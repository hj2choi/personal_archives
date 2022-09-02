# Clean Code (Robert C. Martin)
# Chapter 1: Clean Code
## 요즘 시대에 코드라니??
혹자는 시대에 뒤떨어지는 주제라고 여길지도 모른다.  
- 코드는 더 이상 문제가 아니라 모델이나 요구사항에 집중해야 함  
- 코드를 자동으로 생성하는 시대가 다가온다. 영업 직원이 명세에서 자동으로 프로그램을 생성하면 됨  

<b>댓츠 노노. 그렇지 않다.</b>  
- 코드는 그 자체가 요구사항을 상세하게 표현하는 수단이다.  
- 어느 수준에 이르면 코드의 도움 없이 기계가 실행할 정도의 요구사항을 상세하게 표현하는 것은 불가능하다. 추상화도 불가능하다.  
- 수학이 인간의 부족한 직관을 배제하고 엄밀한 논리에 의지하기 위해서 사용되고 그 덕분에 여러 분야에 응용되듯이, 코드도 마찬가지로 고객의 막연한 직관과 감정만 가지고는 성공적인 시스템을 구현하지 못함.  

<b> 프로그래밍 언어의 추상화 수준은 갈수록 높아질 것. 하지만, 어떠한 간편한 언어나 도구를 사용하든 <i>(요구사항에 더욱 가까운 언어, 혹은 요구사항에서 정형 구조를 뽑아내는 도구 등)</i>, 코드는 수학처럼 언제나 기계가 이해하고 실행할 정도로 엄밀하고 정확하고 상세하고 정형화되어야 함. </b>  


## 나쁜 코드로 치르는 대가
프로젝트 초반에는 번개처럼 나가다가 1-2년만에 굼뱅이처럼 기어가는 팀이 빈번하게 생김.  
코드를 고칠 때마다 엉뚱한 곳에서 문제가 생기기 때문. 매번 얽히고 설킨 스파게티 코드를 '해독'해서 그 위에 얽힌 코드를 더함.  
프로젝트 재설계를 위해 가장 유능한 사람들이 모인 팀이 10년이 넘게 걸리고 포기한다는 도시전설이 존재.  
<b>애초에 처음부터 깨끗한 코드를 만드는 노력을 들여라. 생각보다 재설계의 기회는 다시 돌아오지 않는다.</b>  


## 실무에서 깨끗한 코드를 사수하는 태도
<b>훌륭한 의사는 자신의 클라이언트인 환자의 잘못된 상식과 믿음에 기반한 요구를 단호하게 거부한다. </b>  
프로그래머도 멍청한 관리자, 조급한 고객과 쓸모없는 마케팅 부서 (ㅋㅋㅋㅋ....)를 탓하지 말 것!  
프로그래머들이 자주 기한을 맞추기 위해 나쁜 코드를 양산하는 압력을 받는다. 하지만, 오히려 엉망인 코드로 지불하는 비용이 더 큼.  
https://brunch.co.kr/@leehosung/2  - 기술 부채 참고


## 깨끗한 코드란?
- 한가지를 제대로 잘하는 코드  
- 세세한 사항까지 꼼꼼하게 처리 (오류처리, 메모리 누수, Race Condition, 일관성 있는 명명법 등)  
- 오류처리  
- 의존성과 API를 최소한으로 줄이고 명확히 정의함  
- 클래스, 함수 등을 최대한 줄이고 중복이 없게끔 작성  
- 다른 사람이 고치기 쉬워야 함
- 테스트 케이스 통과


## Boyscout Rule
<b>"Always leave the code better than you found it"</b>  
<i>"캠프장은 처음 왔을 때보다 더 깨끗하게 해놓고 떠나라"</i>  
<br>
한꺼번에 많은 시간을 투자해 코드를 정리할 필요는 없다.  
변수 이름 하나 개선, 조금 긴 함수 하나 분할, 약간의 중복 제거, 복잡한 if문 하나를 정리.  
이런 노력으로도 시간이 지나며 코드의 품질이 퇴보하는 일을 막을 수 있다.  


## Basic OOP design principles
From <i>Agile Software Development: Principles, Patterns, and Practices</i>
- SRP (Single Responsibility Principle): There should never be more than one reason for a class to change.
- OCP (Open Closed Principle): Software entities should be open for extension, but closed for modification.
- LSP (Liskov Substitution Principle): objects of a superclass should be replaceable with objects of its subclasses.
- ISP (Interface Segregation Principle): Clients should not be forced to depend upon interfaces that they do not use.
- DIP (Dependency Inversion Principle): Depend upon abstractions, not concretions


# Chapter 2: Meaningful names
