You don't need to add the remote, just copy the text from Github... it tells you how to do it right in the PR (just click "Use thh command line" to see the instructions)

```
git checkout -b lincolnthree-WINDUP-133 master
git pull https://github.com/lincolnthree/windup.git WINDUP-133
```

Then run the tests "mvn clean install", and if they pass:

```
git checkout master
git merge --no-ff lincolnthree-WINDUP-133
git push origin master
```