import glob
import os
import string

#to get the current working directory name
docsFolder = os.getcwd() + "/docs"
tokenizedDocs = os.getcwd() + "/docInTokens"

#set up for acceptable/unacceptable punctuation 
acceptablePunctuations = {"'", "-"}
punctuation = ''.join((set(string.punctuation)).difference(acceptablePunctuations))
translator = str.maketrans('', '', punctuation)
allPunctTranslator = str.maketrans('', '', string.punctuation)

#statistics 
totalWords = 0

def main():
    i=0
    for filename in os.listdir(docsFolder):
        if filename.endswith(".txt"):
            filePath = os.path.join(docsFolder, filename)
            constructedName = "doc" + str(i) + ".txt" 
            outputFilePath = os.path.join(tokenizedDocs, constructedName)
            out = open(outputFilePath, "w", errors='ignore')
            cleanFile(filePath, out)
        else:
            continue
        i = i + 1 
def cleanFile(file, outputFile):
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
            tem = (word.translate(translator)).lower()
            outputFile.write(tem + "\n")
if __name__== "__main__":
  main()
