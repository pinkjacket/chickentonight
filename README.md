# _Chicken Tonight_

#### _A recipe app incorporating not one but TWO Simpsons jokes, 3/23/2018_

#### By _**James Donlan**_

## Description

_Everybody loves cooking, so here's an app to let you search for recipes using the Yummly API. Just enter your search term and hit the button, and you'll get a scrollable list of recipes! Ability to actually click through to them coming soon. You'll have to make an account, but it just needs an e-mail and a password._

## Setup/Installation Requirements

* _Import project into Android Studio, preferably using the program itself_
* _Gradle will refuse to compile, go to the terminal and create gradle.properties and open it in Atom_
* _Insert your Yummly app ID and key as YummlyId and YummlyToken respectively_
* _Save and resync Gradle (may have to do this twice)_
* _Select "app" in the dropdown menu on the top right next to the green arrow, then click the arrow_
* _Set up an emulated Android phone to run it on. I recommend a Nexus 6 running Marshmallow or above, but anything above Ice Cream Sandwich should be all right_
* _Enjoy!_

## New feature: dragging!
_You can now drag saved recipes around in your saved recipe list and reorder them! Just tap and hold the black drag icon, which is a stack of lines. You can also swipe a recipe off the list to the right to delete it! For that you have to tap and hold anywhere BUT the drag icon.

## A note about branches!
_This project currently features two side branches. One is an attempt to implement the second half of Yummly's two-call API, which would allow this app to display links to actual recipes instead of just vague descriptions. Due to the unique nature of the way the API works I haven't quite got it fully working, but I'd like to when I have time. The other branch implements an alternate layout for a phone held horizontally. That works, but there's not a lot of point to it without more information to show and right now it also causes the recipe search to not display for reasons I'm currently investigating. Hopefully I'll be able to finish and merge these in the future!

## Proposed Future Functionality

* _Implement recipe details, which requires a separate API call (this is almost done, but not quite ready!)_
* _Use images from API results_
* _Make prettier_

## Technologies Used

* _Java_
* _Android_
* _Firebase_

### License

*MIT License*

Copyright (c) 2018 **_James Donlan_**
