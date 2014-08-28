## Get the Windup Source Code

To contribute to the Windup 2.0 project source code, you must fork the Windup repository to your own Git, clone your fork, commit your work on topic branches, and make pull requests back to the Windup repository.

### Install the Git Client

If you don't have the Git client (`git`), get it from: <http://git-scm.com/>

### Fork and Clone the Windup Repository

1. [Fork](https://github.com/windup/windup/fork) the Windup project. This creates the `windup` project in your own Git with the default remote name 'origin'.

2. Clone your fork. This creates and populates a directory in your local file system.

        git clone https://github.com/<your-username>/windup.git

3. Change to the `windup` directory.

4. Add the remote `upstream` repository so you can fetch any changes to the original forked repository.

        git remote add upstream git@github.com:windup/windup.git

5. Get the latest files from the `upstream` repository.

        git fetch upstream

### Submit Code Updates to the Windup Project

1. Create a new topic branch to contain your features, changes, or fixes using the command:

         git checkout -b  <topic-branch-name> upstream/master

   If you are fixing a JIRA, it is a good practice to use the number in the branch name. For example:

        git checkout -b WINDUP-225 upstream/master
2. Make changes or updates to the source files.
3. Use the `git add` command to add new or changed file contents to the staging area.
   
        git add <folder-name>/
        git add <file-name>
           
4. Use the git status command to view the status of the files in the directory and in the staging area and ensure that all modified files are properly staged:

        git status        
5. Commit your changes to your local topic branch. For example:

        git commit -m 'WINDUP-225: Description of change...'     
6. Push your local topic branch to your github forked repository. This will create a branch on your Git fork repository with the same name as your local topic branch name. 

        git push origin HEAD            
   _Note: The above command assumes your remote repository is named 'origin'. You can verify your forked remote repository name using the command `git remote -v`_.
7. Browse to the <topic-branch-name> branch on your forked Git repository. 

         https://github.com/<your-username>/windup/tree/<topic-branch-name> 
8. Open a Pull Request. For details, see [Using Pull Requests](https://help.github.com/articles/using-pull-requests). 

   * Give the pull request a clear title and description.
   * View the modifications that are to be submitted to be sure it contains only the changes you expect.

