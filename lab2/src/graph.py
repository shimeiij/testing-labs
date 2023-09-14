import pandas as pd
import matplotlib.pyplot as plt

csvData = pd.read_csv('./test/resources/LogBase10.0.csv', delimiter=',')
csvData.sort_values(['x'])
print(csvData)
plt.plot(csvData[csvData.columns[0]], csvData[csvData.columns[1]])
plt.gcf().autofmt_xdate()
ax = plt.gca()
ax.set_xlim([0, 20])
ax.set_ylim([-2, 4])
ax.grid(which='major', axis='both', linestyle='-')
plt.show()
