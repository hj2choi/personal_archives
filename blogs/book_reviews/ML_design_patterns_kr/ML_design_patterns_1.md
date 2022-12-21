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
