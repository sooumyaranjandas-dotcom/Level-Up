# LevelUp CI (Any Branch)

This repo is a template that builds an Android APK in GitHub Actions.

## How to use
1. Create a new GitHub repo, upload your Android project **including** the `.github/workflows/build.yml` file from this folder.
2. Go to the **Actions** tab → enable Actions if asked.
3. Either push a commit to any branch or click **Run workflow** on the right.
4. Open the run → download the **LevelUp-debug-apk** artifact.

> Requires Gradle-compatible Android project at `app/` with `assembleDebug` task (standard).