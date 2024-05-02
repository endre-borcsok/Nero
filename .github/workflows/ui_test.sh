name: AndroidBuild

on:
  push:
    branches:
      - main
  pull_request:

jobs:
  test-feature:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v4.1.0
      - name: Set up JDK 11
        uses: actions/setup-java@v3.13.0
        with:
          distribution: 'adopt'
          java-version: '17'

      - name: Grant execute permissions for gradlew
        run: chmod +x ./gradlew

      - name: Run Tests with Gradle
        run: ./gradlew pixel6api31aospDebugAndroidTest