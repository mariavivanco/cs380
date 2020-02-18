import operator
from collections import OrderedDict 

tokenizedDocs = os.getcwd() + "/docInTokens"
docArray = []
invertedIndex = {} 
queries = open("queries.txt", "r")

def directorytoArray (directory):
    
def main():
    #get selected doc 
    selectedDoc = ""
    #get documents  
    docArray = directorytoArray(sys.args[1])
    invertedIndex = createInvertedIndex (selectedDoc, docArray)
    for line in queries:
        query = cleanLine(query) 
        print(query) 
        results = getResults(query)
        print (results) 
def getResults(query):
    #print query 
    queryWords = query.split()
    queryVector = calculateQueryVector(words)
    querySet = set(queryWords)
    print(queryVector)  
    
    #iterate through files and calculate vectors 
    cosineSimiliarityMap = {} 
    docVector = [] # same length as the queryVector 
    i = 0
    for token in querySet: 
        #get tfidf weight
        if token in invertedIndex:
            postingList = invertedIndex[token]["postingList"]
            for doc in postingList.keySet(): 
                docVector[i]
                # cosSim = cosineSimiliarity(queryVector, postingList[doc])
                # cosineSimiliarityMap[doc]  = cosSim
    i = i + 1 
def sortMap (map): 

def cosineSimiliarity(queryVector, docVector):
    #TODO 
    return 0     
def calculateQueryVector: 
    #TODO 
    #return array of vectors 
    return 0
def cleanLine(line):
    return line  
def createInvertedIndex (selectedDoc, docs): 
    # create new dictionary(Hashmap)
    wordMap = dict()
    doc = inputFile
    #iterate through parsed file 
    for word in doc: 
        #if word isn't already in hashmap, create new entry with value 1 
        if word not in wordMap.keys():
            wordMap[word] = 1 
        else: 
            #it is already in hashmap, increment value 
            wordMap[word] += 1 
    totalWords = len(wordMap.keys())
    outputFile.write("unique words: " + str(totalWords)  + "\n")
if __name__== "__main__":
  main()
