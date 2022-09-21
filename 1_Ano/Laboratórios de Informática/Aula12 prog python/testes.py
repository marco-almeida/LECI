def isDigitPresent(x, d):
 
    # Breal loop if d is present as digit
    while (x > 0):
     
        if (x % 10 == d):
            break
 
        x = x / 10
     
 
    # If loop broke
    return (x > 0)
 
 
# function to display the values
def printNumbers(n, d):
    count = 0
    # Check all numbers one by one
    for i in range(0, n+1):
 
        # checking for digit
        if (i == d or isDigitPresent(i, d)):
            count += 1
    return count
 
# Driver code
n = 999
d = 0
print("The number of values are")
print(printNumbers(n, d))
#This code is contributed by
#Smitha Dinesh Semwal