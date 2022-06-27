//============================================================================
// Name        : finalPrep.cpp
// Author      : CHOI, Hong Joon
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <string>
using namespace std;

bool i_compare(const void* i, const void* j)
{
	return static_cast<const int*>(i)>static_cast<const int*>(j);
}


class Sum
{
	private:
		int result;
	public :
		Sum():result(0){}
		void operator()(int op){result+=op;}
		int get_sum(){return result;}
};

class Add
{
private:
	int val;
public:
	Add(int a):val(a){}
	int operator()(int a){return val+a;}
};

template<class T>
class Print
{
private:
	std::ostream& os;
public:
	Print(std::ostream& s):os(s){}
	void operator()(T value){os << value << " ";}
};

void printString(ostream& os,string ss)
{
	os << ss << ", ";
}


int max_subseq_sum_cubic(const int data[], int N)
{
	cout << "cubic algorithm"<<endl;
	int max_sum = 0;
	for (int i=0; i<N; ++i)
	{
		for (int j=i; j<N; j++)
		{
			int sum=0;
			for (int k=i; k<=j; k++)
			{
				sum+=data[k];
			}
			if (sum>max_sum)
			{
				max_sum=sum;
			}
		}
	}
	return max_sum;
}
int max_subseq_sum_quadratic(const int data[], int N)
{
	cout << "quadratic algorithm"<<endl;
	int max_sum = 0;
	for (int i=0; i<N; i++)
	{
		int sum=0;
		for (int j=i; j<N; j++)
		{
			sum+=data[j];
			if (sum>max_sum)
			{
				max_sum=sum;
			}
		}
	}
	return max_sum;
}

inline int max (int a, int b){return (a>b)?a:b;}
int max_border_sum(const int data[], int left, int right)
{
	int max_border_sum=0;
	int border_sum=0;
	for (int i=left; i<right; i++)
	{
		border_sum+=data[i];
		if (max_border_sum>border_sum)
		{
			max_border_sum=border_sum;
		}
	}
	return max_border_sum;
}
int max_subseq_sum_recursion(const int data[], int left, int right)
{
	if (left == right)
		return (data[left]>0)?data[left]:0;
	int center = (left+right/2);
	int left_max_sum = (max_subseq_sum_recursion(data,left,center));
	int right_max_sum = (max_subseq_sum_recursion(data,center+1,right));

	int max_border_sum_max = max_border_sum(data,left,center)+max_border_sum(data,center+1,right);

	return max (max(left_max_sum,right_max_sum),max_border_sum_max);
}
int max_subseq_sum_nlogn(const int data[], int N)
{
	cout << "nlogn algorithm"<<endl;
	return max_subseq_sum_recursion(data, 0, N-1);
	return 0;
}
int max_subseq_sum_linear(const int data[], int N)
{
	cout << "linear algorithm"<<endl;
	return 0;
}

void selection_sort (int* data, int N)
{
	if (N<=1)
		return;

	int min_index =0;
	for (int i=1; i<N; i++)
	{
		if (data[i]<data[min_index])
			min_index=i;
	}

	if (min_index!=0)
	{
		std::swap(data[0], data[min_index]);
	}

	selection_sort(data+1, N-1);
}

void insertion_sort (int* data, int N)
{
	for (int i=1; i<N; i++)
	{
		int insert = data[i];

		int j;
		for (j=i;j>0&&insert<data[j-1];j--)
		{
			data[j]=data[j-1];
		}
		data[j]=insert;
	}
}

void merge(int* data, int* temp, int l_begin, int l_end, int r_end)
{
	int left=l_begin;
	int right=l_end+1;
	int j=l_begin;
	while (left<= l_end && right<=r_end)
		temp[j++] =data[left]<=data[right]?data[left++]:data[right++];
	while(left<=l_end)
		temp[j++]=data[left++];
	while(right<=r_end)
		temp[j++]=data[right++];
}

void mergeSort(int*data, int*temp, int begin, int end)
{
	if (begin<end)
	{
		int center = (begin+end)/2;
		mergeSort(data,temp,begin,center);
		mergeSort(data,temp,center+1,end);
		merge(data,temp,begin,center,end);
	}
}

void mergeSort(int*data, int begin, int end)
{
	int* temp = new int[end-begin+1];
	mergeSort(data,temp,begin,end);
	delete temp;
}

const int& median3(int* data, int begin, int end)
{
	int center = (begin+end)/2;
	if (data[begin]>data[center])
		std::swap(data[begin],data[center]);
	if (data[begin]>data[end])
		std::swap(data[begin],data[end]);
	if (data[center]>data[end])
		std::swap(data[center],data[end]);
	std::swap(data[center],data[end-1]);
	return data[end-1];
}
int partition(int*data, int begin, int end)
{
	const int& pivot = median3(data,begin,end);
	int left = begin;
	int right = end-1;

	std::swap(data[end-1],data[end]);
	while (true)
	{
		while (data[left++]<pivot);
		while (data[right--]>pivot);
		if(left<right)
			std::swap(data[left],data[right]);
		else
			break;
	}
	std::swap(data[left],data[end-1]);
	return left;
}




void quickSort(int* data, int begin, int end)
{
	if (begin<end)
	{
		int pivot=partition(data,begin,end);
		quickSort(data,begin,pivot-1);
		quickSort(data,pivot+1,end);
	}
}

void ISRecur(int A[], int n)
{
	if (n<=1)return;
	ISRecur(A,n-1);
	int temp=A[n-1];
	for (int i=n-2;i>=0;--i)
	{
		if (temp<A[i])
		{
			A[i]=A[i-1];
		}
		else
		{
			A[i]=temp;
			break;
		}
	}
}
int main() {
	int* arr = new int[8];
	arr[0]=4;
	arr[1]=-3;
	arr[2]=5;
	arr[3]=-2;
	arr[4]=-1;
	arr[5]=2;
	arr[6]=6;
	arr[7]=-2;
	for (int i=0; i<8; i++)
	{
		cout << arr[i]<<" ";
	}

	ISRecur(arr,8);
	cout<<endl;
	for (int i=0; i<8; i++)
	{
		cout << arr[i]<<" ";
	}
	int data[] = {4,-3,5,-2,-1,2,6,-2};

/*

	int (*max_subseq[])(const int[], int) = {max_subseq_sum_cubic, max_subseq_sum_quadratic, max_subseq_sum_nlogn, max_subseq_sum_linear};

	for (int i=0; i<4; i++)
	{
		cout << max_subseq[i](data,8)<<endl;
	}




	std::vector<string> people;
	string name;
	//istream_iterator<string> input_iterator(cin);
	while (true)
	{
		cin>>name;
		if (name.size()==1)
			break;
		people.push_back(name);
	}

	for_each(people.begin(), people.end(), Print<string>(cout));
	typedef std::vector<string> strVect;
	strVect::iterator it = people.begin();
	cout <<endl;
	for (;it!=people.end();it++)
		printString(cout,*it);

	strVect friends;
	strVect::iterator friendsIT=friends.begin();
	copy(people.begin(), people.end(), back_inserter(friends));
	cout << "\nfriends\n"<<endl;
	for_each(friends.begin(),friends.end(),Print<string>(cout));

	sort(friends.begin(),friends.end());
	cout<< "people"<<endl;for_each(people.begin(), people.end(), Print<string>(cout));
	cout<<"friends_sorted"<<endl;for_each(friends.begin(),friends.end(),Print<string>(cout));
	cout << endl;
	std::vector<int> x;
	for (int i=0; i<10; i++)
	{
		x.push_back(i);
	}

	transform(x.begin(),x.end(),x.begin(),Add(10));
	for_each(x.begin(), x.end(),Print<int>(std::cout));
	//for (std::vector<int>::iterator it=x.begin(); it!=x.begin()+10; it++)
	//	cout << *it << endl;
	Sum s;
	s = for_each(x.begin(), x.end(), Sum());
	cout << s.get_sum() << endl;
*/
	return 0;
}
