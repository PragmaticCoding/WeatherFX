# WeatherFX

Sample project to show how to build a JavaFX application that "does something"

# Introduction

People seem to get stuck when it comes to turning their JavaFX application into something useful; something that connects to the outside world, or saves and retrieves its data using a database, or interacts with some other application.  I've seen this question asked from both directions:

> How do I connect my JavaFX application to an SQL Database?  I have a screen but I need to be able to save and read information.

or:

> I have an application that does this interesting thing, but I'd like to add a JavaFX front end onto it.  How do I do this?

These are reasonable questions.  How *do* you do this:

# Answer: Use a Standard Framework

The simple answer is to use a standard framework that will allow you to split your application into two parts.  One part is the GUI, and the other is whatever your application actually "does".  Personally, I prefer Model-View-Controller, since it works really well with JavaFX as a Reactive environment.  But that just describes the GUI part of your application.  

At some point, you have to connect your GUI with the part of your application that does the work - which we'll call the "domain" code.  You need an interface that allows the GUI and the domain code to interact in a particular way. I call that the "Interactor".

Now your application has three parts:

- The GUI (Model, View and Controller)
- The Interactor
- The Domain Code


# What this Application Does:

This is a simple application that has all of the components that you'll need to design your own application that "does something".  It has:

- A Reactive MVC GUI
- Application logic in an Interactor
- A weather fetching service
- A domain object
- An external API

Before we go any further, let's see what it looks like when it's running:

![Screen Shot](https://github.com/PragmaticCoding/WeatherFX/blob/master/Weather.png)

It's pretty simple.  You pick a city from a small list in the `ComboBox` then click "Get Weather" and it retrieves the information.  There's only 3 pieces of data: the temperature, a description of the conditions, and an image associated with the conditions.

All of this information comes from a web API from openweathermap.org.  

## API Token

If you want to run this application, you'll need an API Token from openweathermap.org.  You can get one for free from their site, and the link is on the "Pricing" page, under the "Free" column.  You'll probably be redirected to a login/sign-up page before you get there.

[Get An API Token](https://openweathermap.org/price)

When you get a token (it's just a string of characters), you'll need to copy it into a resource file in the project.  Under "resources", create a directory called "data", and then create a file in it called "api.data".  Then just copy your token into it on the first line.  Check the constructor of WeatherFetcher in the source code if any of this is unclear.

The application doesn't have particularly robust error handling, but if it cannot find the token it will give you a message that it had a problem.  If the token is invalid, you just won't get any weather reports in the GUI.

# Read More

There's a whole article about this on my website:  https://www.pragmaticcoding.ca/javafx/weather
