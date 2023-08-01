def merge_sort(list):
    merge_sort_impl(list, 0, len(list) - 1)


def merge_sort_impl(list, left, right):
    # base case
    if left >= right:
        return

    # recursive case
    mid = (left + right) // 2
    merge_sort_impl(list, left, mid)
    merge_sort_impl(list, mid+1, right)
    merge(list, left, mid, right)


def merge(list, left, mid, right):
    list_temp = []
    p1 = left
    p2 = mid + 1

    while p1 < mid + 1 and p2 < right + 1:
        if list[p2] > list[p1]:
            list_temp.append(list[p1])
            p1 += 1
        else:
            list_temp.append(list[p2])
            p2 += 1
    while p1 < mid + 1:
        list_temp.append(list[p1])
        p1 += 1
    while p2 < right + 1:
        list_temp.append(list[p2])
        p2 += 1

    list[left:right+1] = list_temp

list = [1,6,5,3,2,9,12]
print("before merge sort:", list)
merge_sort(list)
print("after merge sort:", list)
