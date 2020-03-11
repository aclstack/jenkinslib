package org.devops

// Node构建命令

def Build(build){
  if ("${build}" == true) {
      buildHome = tool "NPM"
      sh """ 
           ${buildHome}/bin/npm install --registry=https://registry.npm.taobao.org
	   ${buildHome}/bin/npm run build:stage
      """
} else {
   println("非编译项目")
   sh "env"	  
}	
	
	
	


}
