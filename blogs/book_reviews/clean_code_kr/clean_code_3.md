# Chapter 3: Clean Code
## 함수는 작게 만들어라!
작고 더 작게 만들어라.  
각 함수가 너무나 명백한 이야기 하나를 포함해야 한다. 
  
3.1.1 이것보다  
```java
public static String renderPageWithSetupsAndTeardowns(PageData pageData, boolean isSuite) throws Exception {
    boolean isTestPage = pageData.hasAttribute("Test");
    if (isTestPage) {
        WikiPage testPage = pageData.getWikiPage();
        StringBuffer newPageContent = new StringBuffer();
        includeSetupPages(testPage, newPageContent, isSuite);
        newPageContent.append(pageData.getContent());
        includeTeardownPages(testPage, newPageContent, isSuite);
        pageData.setContent(newPageContent.toString());
    }
    return pageData.getHtml();
}
```  
3.1.2 이렇게 함수 길이를 줄여보자  
```java
public static String renderPageWithSetupsAndTeardowns(PageData pageData, boolean isSuite) throws Exception {
    if (isTestPage(pageData))
        includeSetupAndTeardownPages(pageData, isSuite);
    return pageData.getHtml();
}
```  
if, else, while문 등에 들어가는 블록은 <b>한 줄이여야 한다.</b>  
중첩 구조(들여쓰기 수준이 1단,2단 이상)가 생길만큼 함수가 커지게 하지 마라.  

## 한 가지만 하라. 그 한 가지를 잘 해야 한다
위 예제는:  
1. 페이지가 테스트 페이지인지 판단한다.
2. 설정 페이지와 해제 페이지를 넣는다.
3. 페이지를 HTML로 렌더링한다.
  
이것은 추상화 수준이 하나다.  
지정된 함수 아래에서 추상화 수준이 하나인 단계만 수행한다면 그 함수는 한 가지 작업만 하는 것.  
단순히 다른 표현이 아니라 의미 있는 이름으로 다른 함수를 추출할 수 있다면 그 함수는 여러 작업을 하는 셈.  
  

### 함수 내 각 문장 당 추상화 수준은 하나로
3.1.1은 각 문장 별 추상화 수준이 다르다.  
getHtml()은 추상화 수준이 높은 데 비해, 
String pagePathName = PathParser.render(pagepath);는 추상화 수준이 중간임.  
그리고 .append("\n")같은 코드는 추상화 수준이 아주 낮음.  

3.1.1 처럼 한 함수 내에 추상화 수준을 섞으면 특정 표현이 근본 개념인지 세부사항인지 구분하기 어려움.  
더해서 깨어진 창문 이론처럼 사람들이 함수에 세부사항을 점점 더 추가하기 시작함.  

## 위에서 아래로, 내려가기 규칙
코드는 위에서 아래로 이야기처럼 읽혀야 좋다.  
한 함수 다음에는 추상화 수준이 한 단계 낮은 함수가 오게끔 만들어라.  
