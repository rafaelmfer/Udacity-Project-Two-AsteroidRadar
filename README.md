# Project Title

Asteroid Radar

## Getting Started

Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids in a period of time, their data (Size, velocity, distance to Earth) and if they are potentially hazardous.

The app is consists of two screens: A Main screen with a list of all the detected asteroids and a Details screen that is going to display the data of that asteroid once it´s selected in the Main screen list. The main screen will also show the NASA image of the day to make the app more striking.

This kind of app is one of the most usual in the real world, what you will learn by doing this are some of the most fundamental skills you need to know to work as a professional Android developer, as fetching data from the internet, saving data to a database, and display the data in a clear, clear, compelling UI.

### Screenshots

![Screenshot 1](screenshots/screen_1.png)
![Screenshot 2](screenshots/screen_2.png)
![Screenshot 3](screenshots/screen_3.png)
![Screenshot 4](screenshots/screen_4.png)

### Dependencies

```
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
implementation 'androidx.core:core-ktx:1.5.0'
implementation 'androidx.appcompat:appcompat:1.3.0'
implementation 'androidx.constraintlayout:constraintlayout:2.0.4'

implementation 'com.google.android.material:material:1.3.0'

implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
implementation 'androidx.fragment:fragment-ktx:1.3.4'

// Kotlin
def nav_version = "2.3.5"
implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

// Download and parse data
implementation "com.squareup.moshi:moshi:1.8.0"
implementation "com.squareup.moshi:moshi-kotlin:1.8.0"
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-moshi:2.5.0"
implementation 'com.squareup.retrofit2:converter-scalars:2.5.0'

// Kotlin coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1"
implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

implementation "androidx.recyclerview:recyclerview:1.1.0"

// Image downloader
implementation 'com.squareup.picasso:picasso:2.5.2'

implementation "androidx.room:room-runtime:2.2.5"
kapt "androidx.room:room-compiler:2.2.5"

implementation "android.arch.work:work-runtime-ktx:1.0.1"

testImplementation 'junit:junit:4.13.2'
androidTestImplementation 'androidx.test.ext:junit:1.1.2'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
```

### Installation

To get the project running on your local machine, you need to follow these steps:

**Step 1: Clone the repo**

Use this to clone it to your local machine:
```bash
git clone https://github.com/udacity/REPOSITORY_NAME.git
```

**Step 2: Check out the ‘master’ branch**

This branch is going to let you start working with it. The command to check out a branch would be:

```bash
git checkout master
```

**Step 3: Run the project and check that it compiles correctly**

Open the project in Android Studio and click the Run ‘app’ button, check that it runs correctly and you can see the app in your device or emulator.

## Testing

Explain the steps needed to run any automated tests

### Break Down Tests

Explain what each test does and why

```
Examples here
```
## Project Instructions

You will be provided with a starter code, which includes the necessary dependencies and plugins that you have been using along the courses and that you are going to need to complete this project. 

The most important dependencies we are using are:
- Retrofit to download the data from the Internet.
- Moshi to convert the JSON data we are downloading to usable data in form of custom classes.
- Picasso to download and cache images. (You could also use Glide, but we found it has some issues downloading images from this particular API)
- RecyclerView to display the asteroids in a list.

We recommend you following the guidelines seen in the courses, as well as using the components from the Jetpack library:
- ViewModel
- Room
- LiveData
- Data Binding
- Navigation

Android Studio could display a message to update Gradle plugin, or another thing like Kotlin, although it is recommended to have the last versions, it could be you have to do other things in order to make it work.

The application you will build must:
- Include Main screen with a list of clickable asteroids as seen in the provided design.
- Include a Details screen that displays the selected asteroid data once it’s clicked in the Main screen as seen in the provided design. The images in the details screen are going to be provided here, an image for a potentially hazardous asteroids and another one for the non potentially hazardous ones.
- Download and parse data from the NASA NeoWS (Near Earth Object Web Service) API.
- Save the selected asteroid data in the database using a button in details screen.
- Once you save an asteroid in the database, you should be able to display the list of asteroids from web or the database in the main screen top menu.
- Be able to cache the asteroids data by using a worker, so it downloads and saves week asteroids in background when device is charging and wifi is enabled.
- App works in multiple screen sizes and orientations, also it provides talk back and push button navigation.


## Built With

To build this project you are going to use the NASA NeoWS (Near Earth Object Web Service) API, which you can find here.
https://api.nasa.gov/

You will need an API Key which is provided for you in that same link, just fill the fields in the form and click Signup.

## License


