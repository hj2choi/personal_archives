# Machine Learning Design Patterns
## 1.3 Terms used in Machine Learning
### 1.3.1 Model & Framework
머신러닝은 데이터에서 학습하는 모델을 구축하는 프로세스.  
고객의 이사 비용을 추정하는 프로그램 예시:
```python
if num_bedrooms == 2 and num_bathrooms == 2:
    estimated_cost = 1500
elif num_bedrooms ==3 and sq_ft > 2000:
    estimated_cost = 2500
```  
더 많은 변수(대형 가구의 수, 옷의 개수, 깨지기 쉬운 품목 등)와 예외적인 케이스를 추가하다 보면 코드는 순식간에 복잡해짐.  
그 대신, 과거 데이터를 기반으로 비용을 추정하는 ML 모델을 학습할 수 있다.

#### Types of ML models
<pre>
<b>Machine Learning</b>  
|──<b>Supervised Learning</b>  <i># for data with ground truth labels</i>  
|  |──Linear Models  
|  |──Support Vector Machine  
|  |──Decision Trees  
|  |  |──Random Forest  
|  |  |──XGBoost  
|  |  |──LightGBM  
|  |──Neural Networks
|  |  |──Convolutional Neural Network  
|  |  |──Recurrent Neural Network
|──<b>Unsupervised Learning</b>  <i># for data without ground truths</i>  
|  |──Clustering  
|  |  |──DBSCAN  
|  |  |──K-means
|  |  |──https://scikit-learn.org/stable/modules/clustering.html
|  |──Association Rule Learning  
|  |──Dimensionality Reduction  
|  |  |──Principle Component Analysis  
|  |  |──ISOMAP  

and more...
</pre>

- Typically, Feed Forward Neural Network with more than 2 hidden layers are considered deep learning  
- Supervised Learning is subdivided into <b>Regression</b> (Continuous output) and <b>Classification</b> (Categorical output) Models  
- classification problem examples: cat vs dog, document type tagging , fraud detection in financial transactions
- regression problem examples: travel time prediction, company profit estimation, manufacturing cost estimation

### 1.3.2 Data and feature engineering
- Dataset: 모델의 학습, 검증 테스트에 사용되는 모든 데이터
- Training data: 학습 프로세스 중 모델에 제공되는 데이터
- Epoch: 모델의 학습 데이터셋에 대한 각 반복 (횟수)
- Validation data: 학습이 완료된 후 모델의 성능을 평가하는데 사용되는 데이터
- Hyperparameter: 모델에 대해 사용자가 직접 설정해주는 값 (e.g: number of trees in Random Forest).
- Test data: 학습 과정에서 전혀 사용되지 않은 데이터로, 학습이 끝난 모델의 성능을 최종 평가하는데 사용

#### types of data
- Structured data: collection of numerical and categorical data such as CSV tabular data.  
- Unstructured data: e.g) unformatted text, image, video, audio  
  
수치 데이터는 머신러닝 모델의 입력으로 직접 투입할 수 있지만, 다른 데이터는 모델이 이해할 수 있는 수치 형식으로 변환하기 위한 다양한 전처리(Preprocessing / Feature Engineering)가 필요하다.  
  
Feature Engineering을 거친 후에는 데이터 검증을 진행한다. 데이터에 대한 통계를 계산하고, 스키마를 이해해 데이터의 드리프트, 학습 제공 편향, 그리고 각 특징의 불균형 여부 등을 식별한다.  


#### Machine Learning Process
depending on a context, the model serves by either doing prediction or inference on data.
  
데이터 수집 -> 데이터 검증 -> 데이터 준비 -> 모델 학습 -> 모델 평가 -> 모델 검증 -> 모델 서빙 -> 최종 UI

#### Related Field of Expertise
- Data Scientist: 데이터셋 수집, 해석, 처리를 수행한다. 통계적, 탐색적 분석을 수행하고 머신러닝 모델을 가장 먼저 구축함.
- Data Engineer: 조직의 데이터를 위한 인프라와 워크플로우를 관리한다. 데이터 수집, 파이프라인 구축, 저장 및 전송을 담당함.
- Machine Learning Engineer: ML모델을 가져와서 학습, 배포와 관련된 인프라와 운영을 관리함. 모델 업데이트, 버전 관리, 예측 서빙을 처리하는 production system을 구축함.
- Research Scientist: 새로운 알고리즘을 찾고 개발하는 역할을 함. 모델 아키텍쳐, NLP, Vision, hyperparameter tuning 등의 하위 분야 포함. 새로운 ML 접근방식을 프로토타이핑하고 평가함.
- Data Analyst: 데이터를 분석하고 통찰한 내용을 뽑아낸 후, 조직에 공유. Business intelligence 도구를 이용하여 데이터를 시각화하고 결과를 공유한다. 제품 팀과 협업하여 데이터에서 얻을 수 있는 통찰력이 어떻게 비즈니스 문제를 해결하고 가치를 창출하는지에 대해 고민한다.
- Developer: 최종 사용자가 ML 모델에 접근할 수 있는 프로덕션 시스템 구축을 담당한다. 웹/앱을 통해 쿼리를 날리고 예측을 반환받는 API를 설계한다.



