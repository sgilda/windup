> A simple script I use to bring down a bunch of pull requests into a single branch.[1] I then push that branch to upstream master-ignore.

```bash
pull() {
  cmd="git fetch upstream master "
  for var in "$@"
  do
    cmd="$cmd pull/$var/head:pullRequest$var"
  done

  $cmd

  git branch -D pulls
  git checkout master
  git rebase upstream/master
  git checkout -b pulls

  for var in "$@"
    do
    git checkout pullRequest$var
    git rebase pulls
    git checkout pulls
    git merge pullRequest$var
    git branch -D pullRequest$var
  done
}
```


## Alternative

This is the approach GitHub suggests.

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