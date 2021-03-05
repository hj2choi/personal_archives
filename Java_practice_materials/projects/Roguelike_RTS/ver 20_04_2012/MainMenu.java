/*
Hong Joon Choi
Version 0.1
14.04.2012

Objective :
add save
add battlefield
add battle
add accessories
add options, etc..
*/
/*
AI script writing strategies http://www.gpgstudy.com/gpg2/gpg2s3-1draft.html

전략 #3: 관리자를 통한 협동의 집중화
전략 #7: 문제의 일부분만 해결한다.
전략 #8: 힘든 일은 미리 처리한다

전략 #10: 연속적인 갱신을 통한 상각된 질의 비용
이러한 전략이 실제로 쓰이는 예는 세력분포도(influence map)이다. 세력분포도는 전반적인 상황 분석에 쓰이는 것으로, 그러한 상황 분석은 AI의 의사결정 속도를 빠르
게 만든다. 게임이 진행됨에 따라, 유닛들의 상태 정보(이동, 전투, 소멸 등)는 세력분포도에 꾸준히 반영된다. 이미 데이터가 지속적으로 갱신되어 왔기 때문에, 전반적인 
전략 AI가 게임 세계를 분석해야 할 때가 되어도 특별히 할 일은 없다. 그냥 데이터를 읽어서 참고하면 되는 것이다. 이런 방식이면 속도에 큰 영향을 미치지 않고도 세력
분포도를 빠르게 조회할 수 있다. 이 책에 수록된 Paul Tozour의 "세력분포도"에서 세력분포도의 자세한 개념과 활용 방법을 배우게 될 것이다.




&#8226; A* 알고리즘
1. 시작 지점을 노드 P로 둔다
2. 노드 P에 f, g, h 값을 배정한다.
3. P를 open queue에 추가한다. 이 시점에서 open queue에는 P 밖에 없다.
4. open queue의 노드들 중 최선의 노드 (최선의 노드란 f 값이 가장 작은 노드이다. )를 B로 둔다.
a. B가 목표 노드이면 경로를 찾은 것이므로 알고리즘을 끝낸다.
b. open queue 가 비었다면 경로를 찾을 수 없는 것이므로 알고리즘을 끝낸다.
5. B에 연결된 유효한 노드를 C로 둔다.
a. C 에 f, g, h 값들을 배정한다.
b. C가 open queue 나 close queue에 들어있는지 점검한다.
i. 만일 들어있다면, 새 경로가 효율적( f 값이 더 작은지 )인지 점검한다.
1.만일 그렇다면 경로를 갱신한다 
ii. 들어있지 않다면 C를 열린 목록에 추가한다.
c. 단계 5를 B에 연결된 모든 유효한 자식 노드들에 대해 반복한다.
6. 4부터 다시 반복한다.
이 알고리즘을 기본으로 구현 하였으며 시작점을 (sx,sy) 목표점을 (dx,dy) 라고 하였을 때, 
H= |dx-sx|+|dy-sy|
G= 각 단계마다 1씩 증가하는 스텝 값 (시작 시 초기값 0)
F= H+G
로 설정하였다. 그러나 우리가 사용하는 평면 좌표(2차원 좌표)에서는 대각선 이동을 허용하지 않고 상하좌우 이동만을 허용하기 때문에 약간의 문제가 발생한다. 그것은 항상 같은 f값을 가지는 노드가 매 스탭 당 2개씩 나타난 다는 것이다. 그리고 이전 스텝의 최소값과 같은 노드가 계속하여 생겨난 다는 점이다. 예를 들어 목표의 좌표가 (4,4) 이고 현 위치가 (1,1) 에서의 인접한 노드는 (1,0), (0,1), (2,1), (1,2) 이다. 각 노드의 f, h, g 값을 위의 식을 적용하여 살펴보면,
(1, 0) h = 7, g = 1, f = 8
(0, 1) h = 7, g = 1, f = 8



=================길찾기===============
여러 장르의 게임에서 가장 흔히 접할 수 있는 문제는 타일 혹은 3D기반의 게임 환경(world)에서 목적지까지 이동할 수 있는 길(path)을 찾는 것
길찾기 시스템은 캐릭터 행동의 특정 측면들을 보장하는 역할
예를 들어서 캐릭터가 장애물들 사이에 끼어서 움직이지 못하는 상황이 벌어져서는 안 될 것
또한 플레이어가 보기에는 너무 명확한 길을 캐릭터가 쓸데없이 멀리 돌아가는 일도 일어나서는 안 될 것
길찾기는 많은 게임들에서 AI의 기초적인 기능이며 특히 전략시뮬레이션(Blizzard의 스타크래프트 게임과 같은..)과 같은 장르에서 목적지를 마우스로 알려주면 가장 빠른 길을 찾아 해당 장소로 이동해야하는 문제로, 거의 모든 게임에서 필요
이에 대한 해법으로 A*알고리즘을 주로 사용하는데, 이는 경로 찾기 문제에서 비교적 빠른 시간에 목적지까지의 최적 경로를 찾아줌
목적지까지 가는 도중에 게임 진행 중 발생할 수 있는 게임 환경(world)의 변경( 예를 들어, 유일한 경로에 플레이어가 건축물을 건설하여 경로를 차단하는 경우)되는 경우는 A* 알고리즘만으로는 모든 경로 찾기 문제를 해결 할 수 없으며 추가적인 방법이 요구
그러나 대부분의 경우 A* 알고리즘과 다른 해법을 병행하여 해결 가능하며 또한 현재 가장 AI 분야에서 가장 많은 연구가 진행된 분야
다음은 바닥 격자 공간에서의 간단한 길찾기 방법 예
㉠ 각 격자 단위로 A* 알고리즘을 이용하여 이용 가능한 노드를 찾음
㉡ 직선 이동을 위해 시작점에서부터 직선으로 갈 수 있는 최대 지점까지 노드를 검색해서 그 노드까지 직선으로 이동
㉢ 쉽게 인근 구역(region)들을 찾기 위해 각 구역들의 연결 상태들을 구성
다음은 Dijkstra 알고리즘(그래프 이론)을 이용한 최단거리 길찾기의 예
Dijkstra 알고리즘은 각각의 선분에 명백한 가중치를 적용하고, 시작 위치와 다른 모든 노드들에게 갈 수 있는 최단 경로를 제공
이 알고리즘에 나타나는 그래프의 각 노드는 부모 노드와 최적 비용값을 데이터로 갖음
최초에 모든 노드들을 위한 보무 값들은 올바르지 않은 값을 가지며 최적 비용은 무한대(infinity)로 설정되며 시작 노드의 최적 비용은 0으로 설정
최적의 경로를 찾기 위한 방법
우선 반복적으로 노드를 대기 행렬로부터의 가장 낮은 최소 비용으로 대체
그리고 그것들의 각 선분에 대해서 어느 선분에 대한 목표 노드를 위한 현재의 최적 비용이 현재 노드의 비용에 선분의 비용을 더한 것보다 크면 목표 노드를 향한 더 나은 경로를 찾아낸 것
그리하여 목표 노드에 대한 비용을 갱신하고 부모 노드의 정보를 갱신하며, 그들을 현재 노드로 향하게 함
다음은 Dijkstra 알고리즘을 위한 의사코드



================지적 행동===========
게임에서 지적인 행동을 일일이 정의 할 수는 없음
그러나 앞서 언급한 길찾기 역시 게임에 등장하는 플레이 가능한 유닛들의 지적인 움직임을 위해 반드시 요구되는 부분 중 하나
만약 이동 가능한 경로가 있음에도 불구하고 게이머가 원하는 위치로 유닛이 제대로 이동하지 못한다면 게이머는 더 이상 게임을 진행하고 싶지 않을 것
또한, 게이머는 게임에서 상대편과 시시한 플레이를 하기를 원치 않을 것
따라서 게임의 상대편이 지능적인 행동을 하도록 게임을 제작하여 게임에서 큰 즐거움을 느끼도록 해야함
예를 들어, 레이싱 게임의 경우 나와 함께달리는 차량이 너무 느리다거나 너무 앞서 달린다거나 해서는 플레이어의 흥미를 유발할 수 없게 됨
계획
게임이 복잡해지고 고도의 지능화된 캐릭터가 등장할수록 캐릭터는 현재의 행동이 앞으로 어떠한 영향을 주는지를 감안하여 전체적인 계획을 세우고 진행
예를 들어 전략시뮬레이션 게임에서 상대편이 취할 수 있는 행동(상대편의 종족 형태, 주 병력의 공격 유형 등)이나 특징을 감안하여 전체적인 대응 전략(상대편 주 병력의 취약 유형 등을 파악)을 계획하여야 함

===============AI 최적화 전략============
복잡한 AI는 상당한 연산 능력을 요구하며 수많은 자동화 에이전트들이 지능적으로 게임 세계에서 활동하도록 하려면 더욱 더 많은 연산 능력이 필요
원래 게임은 기본적으로 실시간에 병렬적인 기능이 크게 요구되는 응용프로그램이지만 AI의 분야는 특히 요구 됨
모든 AI 에이전트들이 동시에 자신의 생각을 가지고 움직이는 것처럼 보이게 하는 것은 여전히 어려운 문제이며 이를 위한 AI 최적화는 비교적 미개척 분야
그러나 거꾸로 생각하면 이러한 어려움은 병렬성을 AI 분야의 새로운 영역으로 만드는 요인이라 할 수 있음
이러한 병렬성은 AI 에이전트들의 다른 고유 특성들과 마찬가지로 AI에서 요구되는 최적화 대상이 될 수 있음

전략 #1 : 폴링 대신 이벤트 주도적 행동을 사용
이론적으로, 자동화 에이전트는 자신의 세계를 끊임없이 관찰하고 그에 맞게 행동해야 함
게임이 진행되는 매 프레임마다 에이전트들은 자신이 반응해야 할 사건 또는 객체들을 감지
이러한 접근 방식은 엄청난 양의 중복된 계산이 발생한다는 점
즉 이러한 개별적인 폴링(polling)은 자원 낭비가 심하다고 말할 수 있음
이에 대한 대안은 “이벤트 주도적(event-driven) 기법‘을 사용하는 것
예로 골프게임을 들어보자. 골퍼가 버디샷을 준비하고 있으며, 이를 수백명의 관중들이 지켜볼 경우, 만약 공이 홀컵에 들어갔을 때 공이 ”나 들어갔어“라는 메시지를 보낸다고 하자
이는 모든 관중이 공이 움직이는 내내 공을 주시하도록 하는 것 보다 훨씬 더 효율적
만약 이렇게 하나의 공이 아닌 정말 많은 수의 객체가 동시에 발생할 경우(전투에서 수많은 병력이 전쟁을 할 경우와 같은..)엔 더더욱 큰 도움이 될 것
전략 #2 : 중복된 계산을 줄임
게임에는 수많은 AI 에이전트들이 존재하며, 그들은 수시로 아주 많은 계산을 수행한다. 이러한 독특한 환경에서의 속도 및 효율적인 AI 처리를 위해서는 고가의 하드웨어 장비가 지원되지 않는 이상 하드코딩으로 극복하거나 지능적 처리 방식을 사용해야 함
실제로 이 전략의 목적은 AI 에이전트들이 자신의 계산, 처리 결과를 공유하도록 만들어 불필요한 중복 계산을 줄이는데 그 목표
AI 에이전트들 각각이 가지고 있는 정보를 공유할 수 있다면 계산 시간을 상당히 줄일 수 있을 것
간단한 예로 길찾기
전략 시뮬레이션 게임(예 : BLIZZARD의 스타크래프트)에서는 수많은 유닛들이 존재하며 플레이어는 이를 마우스와키보드를 이용해 제어하게 되며 이때 한꺼번에 많은 수의 유닛을 맵의 다른 한쪽으로 이동하도록 명령
각각의 유닛들은 해당 명령에 따라 길찾기 수행을 통해 이동하게 되며 이때 모든 유닛이 길찾기를 수행하는 것 보다 한 유닛이 길찾기를 수행하고 나머지는 그 유닛의 뒤를 따라가는 것이 훨씬 더 효율적일 것
이럴 경우 길찾기 수행의 횟수가 급감하여 계산상의 시간을 상당히 줄일 수 있음
전략 #3 : 관리자를 통한 협동의 집중화
AI를 좀더 빠르고 간단하게 만들기 위한 방법의 하나로 게임내의 AI 에이전트들을 하나의 집단(Group)으로 묘사하는 것
하나의 집단에는 그 집단을 이끄는 역할을 가진 관리자 에이전트가 있을 수 있으며 게임 흐름상의 일부(특히 각 집단별 행동이 요구되는 경우..) 결정권을 관리자에게 위임하는 것
실제로 지시된 역할을 수행하는 것은 관리자 역할을 맡고 있는 에이전트의 AI가 판단하게 됨
예를 들어, 축구 게임에서 “3-5-2” 시스템으로 진형을 갖춘다고 했을 때, 공격수(3)와 미드필드(5), 그리고 수비수(2) 역할의 에이전트가 존재하며, 이들이 효과적인 공격과 수비를 펼치기 위해 진형별 관리자를 둘 수 있음
이와 같은 방법은 공격수가 공격에만 전담하도록 할 수 있으며 이를 통해 수비수나 미드필드와의 역할 혼동을 피할 수 있고 자칫공만을 쫓아 몰려다니는 “동네 축구”를 탈피할 수 있는 축구 게임을 만들 수 있음
전략 #4 : 처리 부담을 여러 프레임들로 분산
이 전략은 길찾기와 같은 CPU의 자원을 많이 소비하는 AI 부분에 사용하면 적절
길찾기의 대상이 되는 검색 공간은 몇 프레임만에 크게 변하는 것이 아니므로 계산을 여러 프레임으로 나누어 수행 가능
이러한 접근방식은 프레임 당 처리 부담을 줄이는데 효과적
전략 #5 : 힘든 일은 미리 처리
고도의 물리학 엔진이 탑재된 게임들이 대거 등장
이러한 게임의 특징은 아주 많은 계산이 요구되는 동작을 주로 수행한다는 것
비행 시뮬레이션이 그 대표적인 예
이러한 게임은 종종 실시간으로 처리하기엔 너무 어려운 문제에 직면하기도 한함
이러한 문제를 해결하기 위해 복잡한 계산을 미리 수행하여 테이블에 담아주고 참조하는 방식을 사용하는 등의 기법을 사용하여 실행 시점에서 단 한순간의 조회만으로 의사 결정과 판단에 필요한 정보를 얻을 수 있게 만드는 것이 가능
이러한 전략은, 단 몇킬로바이트의 데이터로 엄청난 CPU 자원을 절약할 수 있게 해준다는 점에서 매우 강력한 최적화 전략이라 할 수 있음
이 외에도 AI의 최적화를 위한 전략들은 아주 많음
실제로 AI 시스템에서 직면할 수 있는 문제들을 최적화하려면 좀 다른 시각에서 문제를 바라보는 것이 도움이 될 것



=============게임에서 FSM을 사용할 경우==============
우리가 알고있는 유명한 게임 중 퀘이크(Quake)도 NPC(Non Player Character)의 행동 제어에 FSM을 사용하였으며, 이와 같은 장르의 게임인 Half Life 역시 계층적 FSM(Hierachical FSM)을 사용한 것으로 알려져 있음
일반적으로 게임 내에서 캐릭터의 행동이나 감정 상태등을 FSM으로 표현 가능
여러 캐릭터 혹은 하나의 캐릭터에 대한 현재 상태가 주어지고 이후 외부의 자극등에 의하여 상태가 바뀔 경우 현재 상태에 따라 그에 대한 반응 방법이 결정
자극과 같은 외부의 상황이 바뀌면 현재 상태가 다른 상태로 전이
또한 전등 스위치에서와 같이 캐릭터는 특정 상태에 있을 경우에는 항상 같은 방식으로 행동
여러 캐릭터가 몇 개의 상태로 주어질 때 캐릭터의 현재상태에 따라 외부에 대처하는 방식이 결정
즉, 외부의 상황이 변화하게 되면 현재상태도 FSM에 의해 다른 상태로 전이
또한, 캐릭터는 특정한 상태에 있을 경우 항상 같은 방식으로 행동
결과적으로 FSM의 전반적인 아이디어는 매우 간단하고 단순하지만, 그 활용 범위와 용도는 너무나도 다양하다 할정도로 시스템 제어에 근본이 되는 알고리즘이라는 것을 알 수 있음
실제로 단순히 switch() 구문 혹은 if 구문을 사용하여 프로그래밍되는 것이 전부
그래서 FSM은 특별한 인공지능 기능을 요구하지 않는 게임에서 많이 사용하며 만약 설계/구현 과정에서 상태의 수가늘어나게 되면 switch() 구문을 사용하는 대신 배열을 사용하여 원하는 루틴에 빠르게 접근
FSM의 의사코드 구현
Switch(creature_state)
	case STATE_ATTACK :
		플레이어 쪽으로 이동
		20%의 확률로 포 발사
	case STATE_RETREATE :
		플레이어와 반대 방향으로 이동

FSM도 표현해야할 상태수가 늘어나게 되면 몇가지 단점
FSM이 나타낼 수 있는 상태 수는 어느 정도 제한적이지만 사실상 그 수에 대한 제한은 없기 때문에 복잡하고 많은 수의 상태 표현을 요구하는 경우 상태 다이어그램을 정리하기가 어렵고 상태 변화를 가능하게 하는 외부 자극 처리가 복잡해지게 됨
게다가 FSM을 사용한 게임 혹은 게임내의 캐릭터는 그 행동의 예측이 쉬우며 그렇게 될 경우 게임의 재미가 떨어지고, 게임의 난이도를 높이기 위해 FSM을 새로 확장 구현해야하는 결과를 초래
실제로 게임 개발 도중 FSM은 빈번하게 진화, 확장되거나 계층적 FSM으로 다시 설계


============FuSM(Fuzzy State Machine)==============
FuSM은 FSM의 단점을 개선하기 위한 방법으로 퍼지 이론을 FSM의 상태 전이를 위한 조건 판단에 응용한 것
FuSM은 상태의 입력과 출력에 퍼지 함수를 적용하여 랜덤 기능을 추가함으로써 동일한 입력에도 다른 상태로의 전이를 얻을 수 있는 방법
따라서 퍼지 상태 기계는 “그렇다”와 “아니다”라는 이산적인 상태 대신 “매우 그렇다”, “그저 그렇다”, “다소 아니다”, “매우 아니다”와 같은 상태들을 표현
이것은 하나의 (다분이 정량적이지만) 빛을 분산시키는 스팩트럼과 같은 원리라 보면 이해가 빠를 것
실제로 FuSM을 게임에 이용한다면, 예를 들어 하나의 NPC(여기서는 자연 유닛이라 본다면) 는 플레이어에 대해 단순히 “귀찮다”, “조금 귀찮다”, “매우 귀찮다”와 같은 상태로의 전이가 가능한 것
이는 또한 게임 안의 상태들이 반드시 구체적이고 이산적일 필요가 없음을 뜻하기도 함
일반적으로 FuSM은 하나의 상태를 표현하기 위해 일정한 범위를 갖는 실수로 표현
보통 실수의 범위는 0.0에서 1.0사이의 범위를 갖지만 이것이 퍼지 이론에서 말하는 퍼지의 값을 표현하기 위한 유일한 방법은 아님
따라서 사용하고자 하는 수들을 퍼지 값이라고 규정짓고 사용할 수 있음
그렇다면 왜 게임에서 FuSM을 사용하는가? 우리는 게임에 퍼지 논리를 적용시킴으로써 좀더 추상적인 개념을 표현할 수 있으며 이를 통해 게임내의 객체(NPC와 같은..)들로부터 좀더 재미있는 반응을 만들고 좀더 예측하기 힘들게 만듦으로써 게임 전체의 재미를 향상 시킬 수 있기 때문
실제로 게임 플레이어는 마주치는 같은 종류의 NPC 혹은 몬스터(monster)들의 각기 다른 행동 패턴을 느낄 수 있게 되며 이로써 게임은 더욱 더 흥미진진해지는 것
실제로도 같은 케릭터가 다르게 보이기도 함
다양한 게임 장르들에서 FuSM은 유용하게 사용되고 있음
RPG 게임중 하나인 디아블(DIABLO)로에서 캐릭터는 적 유닛들로부터 입은 각종 공격 피해로 인해 행동이 느려진다거나 체력이 줄어든다거나 하는 변화를 수시로 겪음
이는 상당히 많은 상태값을 요구하는데 실제로 적이 사용한 무기 혹은 마법의 능력치에 따라 피해의 크기와 지속 시간등이 다채롭게 변함
레이싱 게임중 하나인 니드포스피드(Need for Speed)에선 경기 도중 코스 이탈을 하거나 차량이 부서지거나하면 피해 위치와 규모에 따라 게임의 속도와 핸들링 등에 다양한 영향을 주게 됨
이는 게임의 진행을 좀더 신중하게 하게끔 유도하며 사실감을 더욱더 느끼는 효과까지 제공
퍼지논리를 컴퓨터를 통해 게임에 적용하는 것은 그리 어려운 것이 아니며, 퍼지의 특성이 적용될 수 있는 다양한 상태의 결정이 요구되는 곳에서 퍼지 논리는 게임의 한 부분으로서 완벽하게 사용 가능



=============영향력 분포도(influence map) 기법=============
영향력 분포도 기법은 전략적 판단을 내리는데 꼭 필요한 게임 AI 기법으로, 많은 게임들에서 유용함이 입증
영향력 분포도는 주로 전략 게임에 쓰이나, 전술적 분석이 필요한 다른 종류의 게임들에서도 유용하게 쓰일 수 있음
영향력 분포도란 AI 에이전트가 세계에 대해 지니고 있는 지식을 공간적으로 표한 것
실제로 게임 환경의 물리적/지형적 표현과 현재의 게임 상태(유닛 및 조형물의 배치등)에 기반해서 전략적 결정에 필요한 정보만을 적절히 추출한 것이라 할 수 있음
컴퓨터 플레이어는 영향력 분포도를 통해 자신의 유닛 배치와 적의 위치, 그리고 적과의 전선 및 전투하기 가장 적절한 곳이 어디인지 등을 파악할 수 있음
영향력 분포도를 만들어 내는 방법은 여러 가지가 있으며 적용 방법 역시도 여러 가지가 있음
여기서는 가장 간단한 수준에서 개념을 알아볼 것
2차원 공간에서의 영향력 분포도
영향력 분포도는 대부분의 게임 구조에 대해서도 적용 가능
여러 가지 타일 기반의 격자(직사각형, 정사각형,육각형 등) 형태를 갖출 수 있으며 3D에서도 적용 가능
여기서는 2차원 공간의 간단한 구조를 통해 이해를 돕겠음
게임 세계는 사격형 칸들로 덮음
그리고 각 칸들의 모든 영향력은 0으로 초기화
그리고 각 칸이 가질 수 있는 영향력 값이 배정
이때 영향력의 값의 기준은 전투 능력등으로 볼 수 있으며 아군은 양수, 적군은 음수의 값을 갖음
하나의 타일로 이루어진 게인 세계에는 고유의 영향력을 갖는 아군과 적군이 공존하며, 이들의 영향력 값을 적절히 더하고 뺌으로써 각 타일에서의 유닛 전투 능력을 평가
일단 각 유닛이 1이라는 전투 능력을 가진다고 하자.
이제 각 칸의 영향력을 인접 칸들로 전파시켜야 함
인접한 칸으로 전파될 때마다 세력(영향력)은 반으로 준다고 가정
두 적군 유닛(비행기)들의 영향력 역시 동일하게 전파되며, 적 유닛의 영향력은 음의 값으로 나타나게 됨
아군 유닛과 적 유닛의 의 영향력을 결합한 분포도를 통해 우리는 아군과 적군의 역관계를 확실히 알 수 있으며 타일 내의 영향력(양의 값과 음의값)을 이용하여 적들의 진형과 전선 형성등을 파악
실제 게임에 쓰이는 분포도의 형태는 좀더 복잡
실제 게임에서는 단지 각 칸에 영향력 값만이 아닌 게임의 세계에 대한 또 다른 정보( 전투능력, 지역 가시성, 전사자 수, 자원, 통행성 등)들을 함께 담는 경우가 많음
이렇게 구성된 영향력 분포도는 하나의 데이터베이스가 되기도 함
일반적으로, 게임에 참여한 플레이어들에게 각자에 해당하는 영향력 분포도가 주어지게 되고 이를 처리하기 위해 분포도를 병렬적으로 처리해야 함
물론 모든 플레이어들에 대해 하나의 단일한 영향력분포도를 갱신하고 모든 AI플레이어들이 그것에 접근하게 만들 수도 있음
이것은 치팅(cheating) 수단이 될 수 있음

==============자원 배정 트리============
영향력 분포도기는 지형 차원의 전술적 자산(asset)을 얻기 위한 수단이라 할 수 있으나 앞으로 살펴볼 자원 배정 트리는 전략적 차원의 결정을 내리는데 사용되는 기법 중 하나라 볼 수 있음
자원 배정트리는 플레이어의 제어 하에 있는 모든 자산들의 특정한 기능적 목적을 표현하는 하나의 트리 구조
이 트리는 현재 플레이되고 있는 모든 유닛들과 자원들을 기능적 범주들의 계통구조로 조직화한 형태
자원 배정 트리를 이용해 플레이어는 자신을 포함한 게임안의 모든 플레이어들의 전략적 강점과 약점을 알아내는 데 사용될 수 있음
또한 이 트리는 다양한 범위의 경제적 생산 및 자원 배분 결정을 위해 사용 가능
예를 들어 전투 유형에 따라 생산할 유닛을 결정한다든지, 그리고 어떠한 기능적 역할을 맡길 것인지를 판단하는데 사용
자원 배정 트리는 기본적으로 어떤 새 유닛들을 생산할 것이며 기존 유닛들에게 어떤 역할을 맡길 것인지를 결정하는데 유용
또한 플레이어의 독특한 개성을 창조하는 수단이 될 수도 있음
즉 개성적인 AI 플레이어들을 만들어내는데 사용 가능
개성적인 AI 플레이어들을 만들어내는 문제는 단지 트리 각 노드의 배분 비율등을 조절하여 해결 가능하며, 또한 각 노드들을 적절히 조절하므로써 전략적 비중을 변경하는 것 또한 가능


*/
class MainMenu extends DataBase
{

	// compatible in 35x100 size terminal
	static Player player[] = new Player[4];
	static DataBase database=new DataBase();

	static int input = 0;
	public static void main(String[] args) 
	{
		
		player[1] = new Player();

		//////////////////////********************Load Data********************//////////////////////
		System.out.println("RTSProject. (Last edit 14.04.2012) HongJoon");
		System.out.println("Enter 1 to load. To generate new profile, enter 0.");
		if (safeInput()!=1)
		{
		}
		else
		{
			try
			{
				player[1].loadPlayer("saveData1");
			}
			catch (Exception e)
			{
				System.out.println("Error has occured while loading the file : "+e);
				return;
			}
			
		}
		System.out.println("Lodaing Successful.");
		//s.next();
		//////////////////////********************Main Menu********************//////////////////////
		//Battle b = new Battle(player[1], player[1].getConstructedUnit(), 8, player[2], player[2].getConstructedUnit(), 3);
		//b.battle();
		





		MainMenuLoop : while (true)
		{ 
			clearscreen();
			System.out.println("			   ========= =Main Menu=========\n\n");
			System.out.println("				[1] Player Statistics");
			System.out.println("				[2] Machine Shop");
			System.out.println("				[3] Enter Campaign\n");
			System.out.println("				[5] Save\n");
			System.out.println("				[7] Options");
			System.out.println("				[8] Manual\n");
			System.out.println("				[0] Exit Game");
			printLine(10);
			switch (safeInput())
			{
			case 1:
				information(1);
				break;
			case 2:
				machineShop(1);
				break;
			case 3:
				campaign(1);
				break;
			case 5:
				save(1);
				break;
			case 7:
				
				break;
			case 8:
			
				break;

			case 0:

				break MainMenuLoop;
			default :
				break;
			}
		}
		
		
		
		

		
		
	}

//========================================PlayerInformation==========================================
	public static void information(int playerNo)
	{
		infoLoop : while (true)
		{
			clearscreen();
			player[playerNo].printPlayerStats();
			printLine(5);
			
			System.out.println(pad("[2] Inventory",20)+pad("[3] Hanger",20)+pad("[4] Lab",20)+pad("[5] Base Skill",20)+pad("[0] Escape",20));
			input=safeInput();
			if (input==2)
			{
				clearscreen();
				System.out.println("\n		[Leg Parts]");
				for (int i=1;i<50 ;i++ )
					if (player[playerNo].getLegOwn()[i]>=1)
					{
						try{System.out.println(pad(i+".",2)+pad(legParts[i].getName(),15)+" x "+player[playerNo].getLegOwn()[i]);}
						catch (Exception e){break;}
					}
				System.out.println("\n		[Body Parts]");
				for (int i=1;i<50 ;i++ )
					if (player[playerNo].getBodyOwn()[i]>=1)
					{
						try{System.out.println(pad(i+".",2)+pad(bodyParts[i].getName(),15)+" x "+player[playerNo].getBodyOwn()[i]);}
						catch (Exception e){break;}
					}
				System.out.println("\n		[Weapon Parts]");
				for (int i=1;i<50 ;i++ )
					if (player[playerNo].getWeaponOwn()[i]>=1)
					{
						try{System.out.println(pad(i+".",2)+pad(weaponParts[i].getName(),15)+" x "+player[playerNo].getWeaponOwn()[i]);}
						catch (Exception e){break;}
					}
				s.next();
				/*
				System.out.println("\n\nEnter 5 to slelect item to discard. Enter other key to continue..");
				if (safeInput()==5)
				{
					System.out.println("Select Inventory section     [1]leg [2]body [3]weapon");
					System.out.println("(You can enter any other key to escape)");
					input=safeInput();
					if (1>input || input>3)
						break;
					System.out.println("Enter name of item to discard");
					if (input==1)
						searchFor(s.next(),legParts,50);
					
				}*/
			}
			else if (input==3)
			{
				clearscreen();
				System.out.println("					[Hanger]\n");
				player[playerNo].printUnitInfo();
				s.next();
			}
			else if (input==4)
			{
				int legSelect;
				int bodySelect;
				int weaponSelect;
				int hangerSelect;
				String name;
				char indicative;

				clearscreen();
				System.out.println("					[Lab]");
				System.out.println("You can construct your own units with parts that you have.");
				do
				{
					System.out.println("Decide which slot in your hanger you are going to put unit in.");
					System.out.println("(Keep in mind that you can escape at any stage by entering 0.)");
					hangerSelect=safeInput();
					if (hangerSelect==0)
						continue infoLoop;
				}
				while (hangerSelect>9 || hangerSelect<1);
				
				do
				{
					System.out.println("\nSelect LEG parts ( ex) Enter 1 if you want Walker)");
					legSelect = safeInput();
					if (legSelect==0)
						continue infoLoop;
					if (player[playerNo].getLegOwn()[legSelect]<1)
						System.out.println("Not enough inventory!");
				}
				while (player[playerNo].getLegOwn()[legSelect]<1);
				System.out.println("\n"+legParts[legSelect]);
				do
				{
					System.out.println("\nSelect BODY parts ( ex) Enter 1 if you want Infantry)");
					bodySelect = safeInput();
					if (bodySelect==0)
						continue infoLoop;
					if (player[playerNo].getBodyOwn()[bodySelect]<1)
						System.out.println("Not enough inventory!");
				}
				while (player[playerNo].getBodyOwn()[bodySelect]<1);
				System.out.println("\n"+bodyParts[bodySelect]);
				do
				{
					System.out.println("\nSelect WEAPON parts ( ex) Enter 1 if you want ArmourGun)");
					weaponSelect = safeInput();
					if (weaponSelect==0)
						continue infoLoop;
					if (player[playerNo].getWeaponOwn()[weaponSelect]<1)
						System.out.println("Not enough inventory!\n");
				}
				while (player[playerNo].getWeaponOwn()[weaponSelect]<1);
				System.out.println("\n"+weaponParts[weaponSelect]);
				System.out.println("\nEnter any key to continuue");s.next();
				clearscreen();
				do
				{
					System.out.println("Enter name of your unit (less than 18 words)");
					name=s.next();
				}
				while (name.length()>18);
				while (true)
				{
					System.out.println("Enter indictive character of your unit in battle (1 word)");
					try
					{
						indicative=firstLetterOf(s.next());
						break;
					}
					catch (Exception e){}
				}
				
				System.out.println("\nHanger number : "+hangerSelect+"\n"+legParts[legSelect]+"\n"+bodyParts[bodySelect]+"\n"+weaponParts[weaponSelect]);
				System.out.println("Enter 1 to confirm your choice. Any other key will delete your decision.");
				if (safeInput()==1)
				{
					player[playerNo].editUnit(name, indicative, hangerSelect, legSelect, bodySelect, weaponSelect);
					player[playerNo].useLeg(legSelect);
					player[playerNo].useBody(bodySelect);
					player[playerNo].useWeapon(weaponSelect);
				}
				else
					continue infoLoop;
				System.out.println("Unit construction successful!! Enter anykey to return..");
				s.next();

			}
			else if (input==4)
			{
				clearscreen();
			}
			else
				break;
		}
		
	}
//=====================================================Shop==================================================
	public static void machineShop(int playerNo)
	{
		shopLoop: while (true)
		{
			clearscreen();
			System.out.println("Your Cash: $"+player[playerNo].getMoney());
			printLine(3);
			System.out.println("			{Machine Shop}\n\n");
			System.out.println("			[1] Leg Parts\n");
			System.out.println("			[2] Body Parts\n");
			System.out.println("			[3] Weapon Parts\n");
			System.out.println("			[4] unit Upgrades\n");
			System.out.println("			[0] Escape");
			System.out.println("");
			System.out.println("");
			System.out.println("\n\n");
			switch (safeInput())
			{
			case 1:// legParts
				clearscreen();
				System.out.println("				[Leg Parts Shop]\n");
				for (int i=1;i<=10 ;i++ )
					System.out.println(pad("[1"+(i<10?"0":"")+i+"] $"+legParts[i].getCost(),10)+legParts[i]+"\n");
				input = safeInput()-100;

				if (0<input && input<=6)
				{
					if (player[playerNo].getMoney()<legParts[input].getCost())
						break;
					player[playerNo].buy(1, input, legParts[input].getCost());
					clearscreen();
					System.out.println("Purchase successful! Enter any key to return...");
					s.next();
				}
				else
					break;
				break;
			case 2:// bodyParts
				clearscreen();
				System.out.println("				[Body Parts Shop]\n");
				for (int i=1;i<=10 ;i++ )
					System.out.println(pad("[2"+(i<10?"0":"")+i+"] $"+bodyParts[i].getCost(),10)+bodyParts[i]+"\n");
				input = safeInput()-200;

				if (0<input && input<=6)
				{
					if (player[playerNo].getMoney()<bodyParts[input].getCost())
						break;
					player[playerNo].buy(2, input, bodyParts[input].getCost());
					clearscreen();
					System.out.println("Purchase successful! Enter any key to return...");
					s.next();
				}
				else
					break;
				break;

			case 3:// weaponParts
				clearscreen();
				System.out.println("				[Weapon Parts Shop]\n");
				for (int i=1;i<=10 ;i++ )
					System.out.println(pad("[3"+(i<10?"0":"")+i+"] $"+weaponParts[i].getCost(),10)+weaponParts[i]+"\n");
				input = safeInput()-300;

				if (0<input && input<=6)
				{
					if (player[playerNo].getMoney()<weaponParts[input].getCost())
						break;
					player[playerNo].buy(3, input, weaponParts[input].getCost());
					clearscreen();
					System.out.println("Purchase successful! Enter any key to return...");
					s.next();
				}
				else
					break;
				break;

			case 4:// Upgrade
				clearscreen();
				int unitSelect;
				System.out.println("				[Unit upgrade Shop]\n");
				do
				{
					System.out.println("Select category of unit in your hanger to upgrade.");
					unitSelect=safeInput();
				}
				while ((unitSelect<1 || unitSelect>9) || player[playerNo].getConstructedUnit(unitSelect).getWepType().equals("-"));
				System.out.println("\n"+player[playerNo].getConstructedUnit(unitSelect).printInformation()+"\n\n");
				if (player[playerNo].getConstructedUnit(unitSelect).getUpgradeCount()>=5)
				{
					System.out.println("Unit is already fully upgraded!! Enter any key to return.");
					s.next();
					break;
				}

				System.out.println(pad("[401] $30000 ", 15)+"Increase max Hp by 5%");
				System.out.println(pad("[402] $30000 ", 15)+"Increase weapon damage by 5%");
				System.out.println(pad("[403] $30000 ", 15)+"Increase weapon accuracy by 5%");
				System.out.println(pad("[404] $30000 ", 15)+"Increase leg stability by 2");
				System.out.println(pad("[405] $30000 ", 15)+"Increase max Load by 5%");
				System.out.println(pad("[406] $30000 ", 15)+"Upgrade watt efficiency of unit by 5%");
				do
					input = safeInput()-400;
				while (input<0 || input>6);
				if (input==0)
					break;
				if (player[playerNo].getMoney()>30000)
				{
					player[playerNo].getConstructedUnit(unitSelect).upgrade(input*100+5);
					player[playerNo].pay(30000);
					System.out.println("Succefully upgraded! Enter any key to continue..");
				}
				else
					System.out.println("Not enough money! Enter any key to return...");
				s.next();
				break;

			case 0 :
				break shopLoop;

			default :
				break;
			
			}
		}
	}

//=====================================================Shop==================================================
	public static void campaign(int playerNo)
	{
		while (true)
		{
			clearscreen();
			System.out.println("			---------Single Player---------");
			System.out.println("");
			System.out.println("");
			System.out.println("			[1] Campaign  (Stage "+player[playerNo].getCampaignStage()+")");
			System.out.println("");
			System.out.println("			[2] Custom match\n\n\n");
			System.out.println("			[3] Connect to network\n\n\n\n");
			System.out.println("			[0] Escape\n\n");
			input=safeInput();
			if (input==1)
			{
				Map map = new Map();
				int[] setting = new int[100];//first two letters : location, third= AreaProperty, last three : occupiedBy, build n Unit
				int[] triggerConditionGroup = new int[20];
				String[] triggerActionGroup = new String[20];
				boolean[] AIcontrol = new boolean[10];

				switch (player[playerNo].getCampaignStage())
				{
				case 0:
					player[2] = new Player();
					player[2].editUnit("armourGun",'a', 1, 1,1,1);
					player[2].editUnit("machine",'m', 2, 2,1,2);
					player[2].editUnit("bazooka",'k', 3, 4,2,3);
					//first two letters : location, third= AreaProperty, last three : occupiedBy, build n Unit
					setting = new int[]{520000,621121,630000,530000,433000,440000,450000,460000,540000, 550000, 560000, 640000,650000,660000,471211};
					triggerConditionGroup = new int[]{0};
					triggerActionGroup = new String[]{"0"};
					AIcontrol = new boolean[]{false, false, false};
					map.editCampaignMap(setting, player);
					map.campaignTriggerSetting(triggerConditionGroup, triggerActionGroup, 0, 10, 5000);
					map.executeCampaign(player, 2, 300, 100, AIcontrol);
				}
			}
			if (input==0)
				break;
			else
				continue;
		}
		







	}


//=====================================================Save==================================================
	public static void save(int playerNo)
	{
		clearscreen();
		try
		{
			player[playerNo].save("saveData1");
			System.out.println("save successful. Enter any key to return...");
		}
		catch (Exception e)
		{
			System.out.println("Error occured while saving the file");
			System.out.println("Caused by : "+e);
			System.out.println("Enter any key to return...");
		}
		
		s.next();
	}
}
/***
widmoor outpost
thanks to crushing victory at widmoor, we have advanced and set up new outpost called earthreach
welcome to starfall. Enemies are attacking at all directions. get an army together to fight them off as fast as you can!
(calm before the storm) You must secure the bridge to protect our newest outpost. you'll need large garrison to hold them off.
(Rush!) The mountain outpost has stirred up a hornet's nest. Supplies of gold and wood are on the way from northwood. defend the outpost at all cost before supplies arrive.
(From scratch) with Hammerton outpost in place, we can break the back of northern emnemies and drive them from their mountain home. our coffers are empty however, so you will have to make best use of resources you gather yourself.
(So much gold!) We need this outpost to stand until we builld up our war chest. As soon as we've secured enough gold, we can push further south. // amass a war chest of 7000 g
(Parched Earth) There is no way you can hold out with spare resources here. Hold out at all cost until reinforcements arrive.


No tech, but there is base upgrades (Up to level 3) : increase income level, max unit count.











***/