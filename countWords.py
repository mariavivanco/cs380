#Scenario 1: just white space, words. txt -> statsWhiteSpace.txt (analysis)
#Scenario 2: w puncuation 
#Keeping puncutation 

import operator
from collections import OrderedDict 

def main():
    #analysis on only whitespace
    statsWhiteSpace = open("statsWhiteSpace.txt", "w+")
    wordsWhiteSpace = open("words.txt", "r")
    tokenization(wordsWhiteSpace, statsWhiteSpace)
    #downcase + whitespaces 
    statsDowncase = open("statsDowncase.txt", "w+")
    wordsDowncase = open ("downCaseWords.txt", "r")
    tokenization(wordsDowncase,statsDowncase)
    #white spaces + non special punctuation 
    statsSpecialPunct = open("statsSpecialPunct.txt", "w+")
    wordsSpecialPunct = open("specialPuncWords.txt", "r")
    tokenization(wordsSpecialPunct, statsSpecialPunct)
    #white spaces + all punctuation 
    statsPunct = open("statsPunct.txt", "w+")
    wordsPunct = open("punct.txt", "r") 
    tokenization(wordsPunct,statsPunct)
    #white spaces + downcase + non special puncutation 
    statsAll = open ("statsAll.txt", "w+")
    wordsAllScenario = open("all.txt", "r")
    tokenization(wordsAllScenario,statsAll)
def tokenization (inputFile, outputFile): 
    wordMap = dict()
    doc = inputFile
    for word in doc: 
        if word not in wordMap.keys():
            wordMap[word] = 1 
        else: 
            wordMap[word] += 1 
    totalWords = len(wordMap.keys())
    outputFile.write("unique words: " + str(totalWords)  + "\n")
    sortedStats = OrderedDict(sorted(wordMap.items(), key=operator.itemgetter(1), reverse=True))
    for word in sortedStats.keys(): 
        outputFile.write(word.strip("\n") + ":" + str(sortedStats.get(word)) + "\n")
if __name__== "__main__":
  main()
