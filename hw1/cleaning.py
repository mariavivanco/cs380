import glob
import os
import string

#to get the current working directory name
docsFolder = os.getcwd() + "/docs"
#token w only white space
words = open("words.txt", "w+")
#word seperation with punctuation 
punctWords = open("punct.txt", "w+")
#word seperation with downcase 
downcaseWords = open("downCaseWords.txt", "w+")
#word seperation with special punctuation
specialPunctation = open("specialPuncWords.txt", "w+") 
#word seperation with special puncutation and downcase 
all = open("all.txt", "w+")

#set up for acceptable/unacceptable punctuation 
acceptablePunctuations = {"'", "-"}
punctuation = ''.join((set(string.punctuation)).difference(acceptablePunctuations))
translator = str.maketrans('', '', punctuation)
allPunctTranslator = str.maketrans('', '', string.punctuation)

#statistics 
totalWords = 0

def main():
    for filename in os.listdir(docsFolder):
        if filename.endswith(".txt"):
            filePath = os.path.join(docsFolder, filename)
            cleanFile(filePath)
        else:
            continue
def cleanFile(file):
    #clean headers + ending
    doc = open(file, errors='ignore')
    for line in doc:
        header = "PREFACE" in line
        if header == True:  
            break
    for line in doc:
        end = "*** END" in line
        if end == True:
            break
        if "CHAPTER" in line: 
            continue 
        for word in line.split():
            #just white spaces 
            words.write(word + "\n")
            #special punctuation 
            tempWord = word.translate(translator)
            specialPunctation.write(tempWord + "\n")
            #all punctutation 
            temp =  word.translate(allPunctTranslator)
            punctWords.write(temp + "\n")
            #downcase 
            tempWord = word.lower()
            downcaseWords.write(tempWord + "\n")
            #all scenarios
            tem = (word.translate(translator)).lower()
            all.write(tem + "\n")
if __name__== "__main__":
  main()
