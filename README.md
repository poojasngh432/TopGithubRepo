# Trending Github Repositories App in Android(Kotlin)

An Android App that shows the most trending github repositories sorted by number of stars.

<img src="https://github.com/poojasngh432/TopGithubRepo/blob/master/media/repo_list.gif" width="200" style="max-width:100%;">   <img src="https://github.com/poojasngh432/TopGithubRepo/blob/master/media/repo_detail.gif" width="200" style="max-width:100%;"></br></br>

#### Features
* Users can view the most trending github repositories sorted by stars.
* Users can search for repositories based on language.
* Users can view details of a repository in the app.


#### App Architecture 
Based on mvvm architecture and repository pattern.

<img src="https://github.com/anitaa1990/Github-Trending-Repos/blob/master/media/3.png" width="500" style="max-width:500%;">
 
 #### The app includes the following main components:

* A local database that servers as a single source of truth for data presented to the user. 
* A web api service.
* A repository that works with the database and the api service, providing a unified data interface.
* A ViewModel that provides data specific for the UI.
* The UI, which shows a visual representation of the data in the ViewModel.
* Unit Test cases for API service, Database, Repository and ViewModel.

#### Technologies used
* <b>Navigation Components</b> 
* <b>Fragments</b> 
* <b>Drawer Layout</b>
* <b>Picasso</b> 
* <b>RxJava</b>
* <b>Data Binding</b>
* <b>ViewModel</b> 


#### Libraries used
* <b>ConstraintLayout</b> 
* <b>CardView</b> 
* <b>Retrofit 2</b>
* <b>Picasso</b> 
* <b>RxJava</b>
* <b>RecyclerView</b>

#### App Packages
* <b>data</b> - contains 
    * <b>local</b> 
    * <b>remote</b> 
	     * <b>api</b> - contains the api classes to make the api calls to the server, using Retrofit client. 
         * <b>model</b> 
	     * <b>response</b> 
	* <b>repository</b> - contains the repository classes, responsible for triggering api requests and saving the response in the database.
* <b>viewmodel</b>   
* <b>view</b> - contains classes needed to display Activity.
         * <b>adapter</b> - contains adapters for the recycler view
         * <b>ui</b> - contains the fragments and activities for Repositories List and Repository detail page.
	     * <b>viewholder</b> 
* <b>utils</b> - contains values for Constant variables within the app.


#### App Specs
* Minimum SDK 23
* [Java8]
* MVVM Architecture
* Android Architecture Components (LiveData, Navigation Components, Lifecycle, ViewModel, ConstraintLayout)
* [RxJava2](https://github.com/ReactiveX/RxJava) for implementing Observable pattern.
* [Retrofit 2](https://square.github.io/retrofit/) for API integration.
* [Gson](https://github.com/google/gson) for serialisation.
* [Okhhtp3](https://github.com/square/okhttp) for implementing interceptor.
* [Picasso](http://square.github.io/picasso/) for image loading.
