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
## 의도를 분명히 밝혀라
변수/함수/클래스 이름은 주석이 없더라도 다음 질문에 답할 수 있어야 한다.  
-존재 이유는?  
-수행 기능은?  
-사용 방법은?  

### 지뢰찾기 게임 예시  
#### 1st iteration:  
java  
```java
...... List<int[]> theList = ...
public List<int[]> getThem() {
  List<int[]> list1 = new ArrayList<int[]>();
  for (int[] x: theList)
    if (x[0] == 4)
      list1.add(x);
  return list1;
}
```  
python  
```python
theList = [[1, "cell1", 0, 0], [0, "cell2", 0, 1], [4, "cell3", 1, 0], ...]
def getThem():
  list1 = []
  for x in theList:
    if x[0] == 4:
      list1.append(x)
  return list1
```  
- theList에 무엇이 들었는가?  
- theList에서 0번째 값이 왜 중요한가?  
- 값 4는 무슨 의미인가?  
- 함수가 반환하는 리스트 list1을 어떻게 사용하는가?  


<b>이 질문에 대한 정보 제공은 충분히 가능하다.</b>  
- 게임판에서 각 칸은 단순 배열로 표기함.  
- 배열에서 0번째 값은 칸 상태를 뜻함.  
- 값 4는 깃발이 꽃힌 상태를 뜻함.  


#### 2nd iteration:  
java  
```java
public static final int FLAGGED = 4;
...... List<int[]> gameBoard = ...
...
public List<int[]> getFlaggedCells() {
  List<int[]> flaggedCells = new ArrayList<int[]>();
  for (int[] cell: gameBoard)
    if (cell[0] == FLAGGED)
      flaggedCells.add(cell);
  return flaggedCells;
}
```  
이름만 바꿈으로서 코드가 한층 더 명확해짐.  
각 cell에 대해 배열을 사용하는 대신, 클래스를 사용해보자.

#### 3rd iteration:  
java  
```java
...... List<Cell> gameBoard = ...
public List<int[]> getFlaggedCells() {
  List<Cell> flaggedCells = new ArrayList<Cell>();
  for (Cell cell: gameBoard)
    if (cell.isFlagged())
      flaggedCells.add(cell);
  return flaggedCells;
}
```  
python  
```python
def getFlaggedCells():
  flaggedCells = []
  for cell in gameBoard:
    if cell.isFlagged():
      flaggedCells.append(x)
  return flaggedCells
```  


## 그릇된 정보를 피하라
1. 널리 쓰이는 의미가 있는 단어를 다른 의미로 사용하지 마라.  
예시 1: 여러 계정을 그룹으로 묶을 때, 실제 List가 아니라면 accountList라고 명명하지 말 것. 대신, accountGroup, bunchOfAccounts 혹은 Accounts라고 명명.  
예시 2: temperature를 temp로 줄여 변수, 디렉토리 이름으로 사용하면 temporary의 temp와 비교가 안됨.  

2. 유사한 개념은 유사한 표기법, 그렇지 않으면 다른 표기법을 사용하라.  
XYZControllerForEfficientHandlingOfStrings  
XYZControllerForEfficientStorageOfStrings  
이런거 하지 말것.  

3. O vs 0, l vs 1 놀이 하지 말 것.
```java
int a = l;
if (O == l)
  a = O1;
else
  l = 01;
```  


## 의미 있게 구분하라
1. 이름이 다르면 의미도 달라야 함.  
class라는 변수를 이미 사용했다고 klass 변수를 쓰는 짓 같은거 하지 말 것.  

```java
copyChars(char a1[], char a2[])
```  
보다는  
```java
copyChars(char source[], char destination[])
```  
을 사용하기를.  

2. 불용어(noise word)를 금할 것.
두개의 다른 클래스를 ProductInfo, ProductData 같은 이름으로 구분하지 말 것. 여기서 Info와 Data같은 단어는 ,a,an,the처럼 의미가 더해지지 않는 불용어임.  

간단한예시
| Good | Bad |
| --- | ---|
| Account | AccountInfo |
| Cell | CellData |
| Name | NameString |
| Customer | CustomerObject |


## 발음하기 쉬운 이름을 사용하라
프로그래밍은 사회활동이다.  
발음하기 어려운 변수는 토론하기도 어렵다.  

## 인코딩을 피하라
대개 새로운 개발자가 익혀야 할 코드의 양은 상당히 많음. 여기에 인코딩 규칙까지 익히라고 요구하는건 비합리적.  

하지만 대부분 옛 언어와 옛 개발 방식에 쓰이는 변수 명명법. (헝가리식 표기법, 멤버 변수 접두어 등)  
요즘은 잘 사용되지 않는 방식으로 알 고 있지만, 그래도 주의사항 몇가지:  

예 1: 멤버변수를 이름을 description 대신 m_description으로 멤버변수를 알리게끔 표시.  
예 2: PhoneNumber phoneString; 처럼 변수 이름에 타입까지 표시. 타입을 refactoring해도 이름은 바뀌지 않을 위험이 있음.

인터페이스 클래스와 구현 클래스의 Factory함수를 예로 들면, 클래스 사용자는 이게 인터페이스라는 사실을 알 필요 없이 그냥 ShapeFactory 아는게 더 좋은 이름인 듯.  
굳이 IShapeFactory같은 이름을 쓸 필요는 없는 것 같다.  

## 자신의 기억력을 자랑하지 마라
일반적으로 프로그래머들은 아주 똑똑하다 (과연...???).  
하지만 그<strike>놈</strike>들은 때때로 자신의 정신적 능력을 과시하고 싶어함.  
r이라는 변수가 호스트와 프로토콜을 제외한 소문자 URL이라는 사실을 혼자만 암.  
똑똑하기만 한 사람이 아닌, 전문가 프로그래머들은 <b>명료함이 최고</b>라는 사실을 이해하고 남들이 이해하는 코드를 내놓는다.  

## 클래스 & 메서드 이름
#### 클래스 이름
동사는 지양하고 명사나 명사구 사용.  
| Good | Bad |
| --- | ---|
| Customer | Manager |
| WikiPage | Processor |
| Account | Data |
| AddressParser | Info |

#### 메서드 이름
동사나 동사구 사용.
| Good | Bad |
| --- | ---|
| postPayment |  |
| deletePage |  |
| save |  |

Constructor를 overload할 때는 정적 팩토리 메서드 사용.  
```java
Complex fulcrumPoint = new Complex(23.0); // okay, but not representative
Complex fulcrumPoint = Complex.FromRealNumber(23.0); // better. uses factory pattern with descriptive method name.
```  
생성자 사용을 제한하려면 해당 생성자를 private로 선언하라.

#### Note: Java의 경우
클래스: PascalCase
메서드: camelCase
변수: camelCase

#### Note: Python의 경우
클래스: PascalCase
메서드: snake_case
변수: snake_case

## 한 개념에는 한 이름만

## 말장난을 하지 마라

## 기술(전산, 알고리즘, 설계패턴, 수학) 영역에서 먼저, 그게 없을 때 문제 (도메인) 영역에서 가져온 이름을 사용하라

## 의미있는 맥락을 추가하고 불필요한 맥락을 없애라
