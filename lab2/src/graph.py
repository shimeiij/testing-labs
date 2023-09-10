import pandas as pd
import matplotlib.pyplot as plt

csvData = pd.read_csv('./test/resources/CosFunc.csv', delimiter=',')
csvData.sort_values(['x'])
print(csvData)
plt.plot(csvData[csvData.columns[0]], csvData[csvData.columns[1]])
plt.gcf().autofmt_xdate()
ax = plt.gca()
ax.set_xlim([-5, 5])
ax.set_ylim([-5, 5])
ax.grid(which='major', axis='both', linestyle='-')
plt.show()
