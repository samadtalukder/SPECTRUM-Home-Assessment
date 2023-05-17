# SPECTRUM-Home-Assessment
This project only for SPECTRUM Home Assignment. The project is developed by Kotlin and follows the Model-View-ViewModel (MVVM) design pattern to separate the presentation 
logic from the business logic. Coroutines are also used to perform asynchronous operations in a more efficient and readable manner. 
The Material Design framework is used for the UI components, providing a modern and consistent look and feel. In addition, 
Hilt-Dagger is used as a dependency injection framework to manage the app's dependencies.

## Challenge Instructions (Android Developer)
1. Design and develop the required screens (Kotlin)
2. Follow standard architectural patterns (preferably MVVM)
3. Use material design components and follow UI/UX guidelines
4. Layout should be user friendly
5. Make sure your progress is properly conveyed through local git commit history

## Screens A- Home Page
The user is expected to land on this page every time the app is opened. Home page consists
of 4 tabs that lists movies as listed below,
1. Now Playing -> https://api.themoviedb.org/3/movie/now_playing?page=1
2. Popular -> https://api.themoviedb.org/3/movie/popular?page=1
3. Top Rated -> https://api.themoviedb.org/3/movie/top_rated?page=1
4. Upcoming -> https://api.themoviedb.org/3/movie/upcoming?page=1

## Genre
Use Genre API to fetch the list of available genres and store them locally to map them to the
movie objects before displaying the data

### Requirements
1. Endless scrolling to be enabled on each page, {page} param can be modified on the
API to get results for different pages
2. Each movie card should be designed to show information such as, poster, title,
genre, release date (dd/mmm/yyyy), vote average and vote count
3. Clicking on a movie card should redirect the user to the movie details page

## Screens B- Movie Details Page
### Requirements
1. The details page is expected to have detailed information about the movie such as,
backdrop, poster, title, tagline, overview, genre, release date (dd/mmm/yyyy), vote
average, vote count, spoken languages and status in a user-friendly design
2. The details page should provide a CTA for the user to mark a movie as favourite

## Screens C- Search
### Requirements
1. Endless scrolling to be enabled on each page, {page} param can be modified on the
API to get results for different pages
2. Each movie card should be designed to show information such as, poster, title,
genre, release date (dd/mmm/yyyy), vote average and vote count
3. Clicking on a movie card should redirect the user to the movie details page

## Screens D - Favourites
### Requirements
1. Each movie card should be designed to show information such as, poster, title,
genre, release date (dd/mmm/yyyy), vote average and vote count
2. Clicking on a movie card should redirect the user to the movie details page

## Architecture 
The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

## Used Components/Libraries
* Android SDK
* Kotlin
* MVVM Architecture
* Kotlin Coroutines
* Kotlin Flow
* View Binding
* Retrofit2
* OkHttp3
* Room Persistence Library
* Navigation Component
* Glide
* Hilt
* Paging 3

## Run
* Clone the repository into your local machine
* Open Android Studio and navigate to the directory where you cloned the repository
* Wait for the Gradle build to finish
* Connect your Android device to your computer or launch an emulator
* Click the "Run" button in Android Studio and select your device/emulator


