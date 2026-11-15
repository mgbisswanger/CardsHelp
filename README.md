<h1>CardsHelp</h1>

<h2>About the app</h2>
This app allows users to input their current hand in poker and receive information on the type of hand they are holding (for example, a flush or one pair) and its rank. 
<br>Users can input their hand in one of three ways: <br>

- Taking a photo of their hand
- Adding a jpg file that contains an image of a hand to the assets folder and choosing that file from a dropdown in the app
- Manually entering the card values and suits in the dropdown menu of the app

Users can also view their past hands that they have evaluated along with the evaluated rank of each hand.

<h2>Configuration and Setup</h2>
To run the app on your machine, clone the repository and open the project package in Android Studio. If you are running on an emulator, go to Device Manager -> options (the three vertical dots next to your device) -> Edit, and set Camera (either front or rear or both) to webcam. This will allow "Take Photo" to work on an emulated device.<br><br>
The app connects to Gemini's API for card recognition, so ensure you're computer has an internet connection for the card recognition feature to work.<br><br>
Build and run to start interacting with the app.

<h2>Design Choices</h2>

- Our development team built the project to the specs of the requirements document provided to us. However, because camera usage is being handled by the system camera app,
and manual input is being handled by the evaluation screen, there was no justifiable reason to include a Take Photo screen inside the app. The screen that appears when 
a user clicks on "Take Photo" is not managed by the application; it's managed by the phone's operating system.
- Also, in the pursuit of making the app accessible for users not using a camera, we have included a feature that allows them to manually input the cards in their hand
in the dropdowns. This feature was somewhat implied by the requirements document with manual adjustment of the results of the card recognition function, but our app
explicitly includes a feature that allows users manual input without having to run the card recognition function first.
