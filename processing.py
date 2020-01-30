
import glob
import os

#to get the current working directory name
docsFolder = os.getcwd() + "/docs"
words = open("words.txt", "w+")

def main():
  for filename in os.listdir(docsFolder):
      if filename.endswith(".txt"):
          filePath = os.path.join(docsFolder, filename)
          cleanFile(filePath)
      else:
          continue

def cleanFile(file):
    #clean headers
     doc = open(file,"r");
     for line in doc:
         header = "*** START" in line
         if header == True:
             break
     for line in doc:
        end = "*** END" in line
        if end == True:
            break
        for word in line.split():
            words.write(word + "\n")
if __name__== "__main__":
  main()
