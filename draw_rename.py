import os
filelist = os.popen("dir /b /a-d").read()
ff = str(filelist).split("\n")
for file in ff:
	uzanti = file[file.rindex("."):]
	print uzanti
        if uzanti=="DbMaker.py" or uzanti=="rename.py" or uzanti=="Sorular.db":         
                print "atla"
        else:
                newname = "draw_" + file[:file.rindex(".")]
                print newname.lower().replace(".","").replace("-","_");
                print "ren " + file + " --> " + newname + uzanti
                command = "ren " + file + " " + newname+uzanti;
                exec("os.system(command)")
	
