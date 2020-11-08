package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.helpers.ssml.break1s
import com.justai.jaicf.helpers.ssml.break300ms
import com.justai.jaicf.helpers.ssml.breakS
import com.justai.jaicf.model.scenario.Scenario



object Trial02: Scenario() {
    init {

        var item = 0

        state("start") {
            activators {
                regex("/start")
                intent("boot")
            }
            action {
                reactions.run {
                    item = 0
                    image("https://geekandsundry.com/wp-content/uploads/2016/06/image3.gif")
                    say("Cold Steel. A blinding neon light overhead. Once again you wake up the table of a street-doc")


                    sayRandom(
                        "'Bad night, huh?' the doctor says, attaching a rusty mechanical leg to your body.",
                        "'Wakey wakey, sleeping beauty' the doc says sarcastically, affixing your new aluminium hand to your arm.",
                        "'Damn, what a mess you've made of yourself' the doc says full of pity, closing up your metal breastplate."
                    )
                    breakS(3)
                    say("As you walk out into the dim lit streets, there is only one line glowing in stinging red across your mechanical retina."
                    )
                    break1s
                    say("REACH THE EDGE OF THE CITY BY SUNLIGHT AND YOU GET YOUR FREEDOM"
                    )
                    image("http://www.djfood.org/wp-content/uploads/2012/11/tumblr_me7ykg1Nvl1qavwd8.gif")
                    break1s
//                    say("Where do you want to go?")
                    val wohin = (1..3).shuffled().first()
                    val wohin2 = "/start/"
                    val wohin3 = StringBuilder()
                    wohin3.append(wohin2).append(wohin)
                    val wohinfin = wohin3.toString()

                    go(wohinfin)

//                    buttons(
//                        "Mafia",
//                        "Mutant"
//                    )
                }
            }
        }

        //MAFIA ENCOUNTER
        state("/start/1"){
            activators{
                intent("mafia")
            }
            action{
                reactions.run{
                    say("Out of the shadows from across the street steps a well-dressed - and heavily armed - gentleman, obviously a Mafia agent.")
                    image("https://i.pinimg.com/originals/26/de/36/26de36b97478951e240ee56a441a028d.jpg")
                    say("You know you only have a split-second to decide your next move if you want to get away - and with the asset.")
                }
            }
        }

        state("/start/1/success"){
            activators {
                intent("1success")}
            action{
                reactions.run {
                    sayRandom(
                            "You were quick enough, ducked back into the shadows, into a taxi and got away.",
                            "Quick like a cat you dart away, into a cab on the way to the city edges"
                    )
                    go("/start/merch")
                }
            }
        }

        state("/start/1/fail"){
            activators {
                intent("1fail")}
            action{
                reactions.run{
                    say("'Wrong move, friend' says the man, quick like lightning, cutting you down with one strike of a hidden energy-sword.")
                    say("You retain consciousness for long enough to feel how you body is disassembled in search for the asset.")
                    say("Thank you for playing, and better luck next time!")

                    buttons(
                            "Reboot"
                    )
                }

            }
        }

        //MUTANT ENCOUNTER
        state("/start/2"){
            activators{
                intent("mutant")
            }
            action{
                reactions.run{
                    say("You hear a howl, that makes even your synthetic skin crawl. You see two red glowing dots and shining teeth quickly approaching.")
                    image("https://c1.scryfall.com/file/scryfall-cards/art_crop/front/b/9/b9e03567-c95a-40b8-a75a-971076093f57.jpg?1557576381")
                    sayRandom(
                            "A mutant hound is on the hunt in the cover of night - and you are on the menu! You have to decide quickly!",
                            "An augmented tracking dog is quickly approaching you, its teeth bare and ready to strike!",
                            "A herd of wild muts run through the streets, hungry for the little real meat you have on your artificial bones!"
                    )
                }
            }
        }

        state("/start/2/success"){
            activators {
                intent("2success")}
            action{
                reactions.run{
                    sayRandom(
                            "Between fight or flight you did choose the right path this time, and with one sure strike of your metal fist you scare the beast away.",
                            "You know there is no way to run with your cheap legs, so you put all the power your servos can muster into one kick - and manage to fend of the beast."
                    )
                    say("You find what seemed to be either a mechanical teeth of the creature or some other metal part that it was carrying with it. Seeing it is shiny, you take it with you.")
                    item = 1
                    go("/start/merch")
                }
            }
        }

        state("/start/2/fail"){
            activators {
                intent("2fail")}
            action{
                reactions.run{
                    sayRandom(
                            "To run from a hound is like to run from a train - futile. It's teeth rip and tear as your sight fades to black."
                    )
                    say("Thank you for playing, and better luck next time!")
                    buttons(
                            "Reboot"
                    )
                }
            }
        }

        //MERCHANT ENCOUNTER
        state("/start/merch"){
            action{
                reactions.run{
                    say("You duck away into a dark alley to quickly catch your breath. From behind you hear a raspy voice calling out")
                    image("https://i.pinimg.com/originals/f9/d1/65/f9d16599215e38586335d6878ff748c6.jpg")
                    say("Hello friend. I have the finest wares in all of the night streets. Maybe we can strike a deal?")
                    buttons(
                            "Yes",
                            "No"
                    )
                }
            }
        }

        state("/start/merch/yes"){
            activators{
                intent("merchyes")
            }
            action{
                reactions.run{
                    if (item == 1){
                        say("The merchant looks at the shiny metal piece, and trades it with your for a small handgun with a single shot")
                        item = 2
                        say("He steps back into the shadows and you are alone once again.")
                        go("/start/4")
                    }
                    else {
                        say("The merchant has a small handgun, you would love to have with out. 'Ah I'm sorry friend, but no cash means no gun'.")
                        say("He steps back into the shadows and you are alone once again.")
                        go("/start/4")
                    }
                }
            }
        }

        state("/start/merch/no"){
            activators{
                intent("merchno")
            }
            action{
                reactions.run{
                    sayRandom(
                            "You know better than to trust a stranger at night, and walk away.",
                            "'nO dEaL' you growl as you bolt in the opposite direction"
                    )
                    go("/start/4")
                }
            }
        }

        //BAR ENCOUNTER
        state("/start/3") {

            action {
                reactions.run {
                    say("You stay in the shadows not to raise suspicion, and duck through a small door.")
                    image("https://worldofweirdthings.com/wp-content/uploads/retro_cyberpunk_bar_2500-1200x672.jpg")
                    sayRandom(
                            "The room is lit by a flickering neon light, and is filled with leather-clad patrons - you stumbled into a street gang bar.",
                            "As your eyes readjust to the light you can see what once must have been a bustling nightclub, now reduced to a sorry state."
                    )
                    say("'You want something? Only synthenol on the menu here' the barkeeper asks as you sit down at the counter.")
                }
            }
        }

        state("/start/3/yes"){
            activators{
                intent("3yes")
            }
            action{
                reactions.run{
                    say("The barkeeper shoves a dirty glass with a milky liquid in it over the counter in your direction. Your mechanical arm reacts quick and you catch it before it can fall on the floor.")
                    say("As you look around, you see a passed-out patron next to you. His hand lies on the counter, a small handgun under it. A dangerous situation, but if you could get the gun, you would increase your chances of survival by a lot.")

                    buttons(
                       "grab the gun",
                       "leave"
                    )
                }
            }
        }

        state("/start/3/yesfail"){
            activators{
                intent("3yesfail")
            }
            action{
                reactions.run{
                    say( "The risk is too great for the reward. You finish whatever passes as a drink and move back out on the streets.")
                    go("/start/4")
                }

            }
        }

        state("/start/3/yessucc"){
            activators{
                intent("3yessucc")
            }
            action{
                reactions.run{
                    say("Your nimble fingers of metal grab the gun and carefully pocket them without waking up the drunk man. As a consolidation, you leave your 'drink' for him behind before moving out of the bar.")
                item = 2
                go("/start/4")
                }
            }
        }

        state("/start/3/no"){
            activators{
                intent("3no")
            }
            action{
                reactions.run{
                    sayRandom(
                            "You know better than to try to get drunk on a run, and move back on the streets.",
                            "You shake your head, running back out on the streets."
                    )
                    item = 0
                    go("/start/4")
                }
            }
        }

        //FINAL ENCOUNTER
        state("/start/4") {
            action {
                reactions.run{
                    say("You can almost see it, the edge of town.")
                    say("From behind, you hear a raspy voice, full of desperation: 'stop right there'")
                    say("It's another Courier. Their skin shows rashes, and one of the mechanical legs is missing some motors.")

                    if(item==2){
                        say("The city is a dark place. One where you have to fight for survival in any way.")
                        say("You pull your handgun. Thankfully, its sight is alone for your attacker to walk away.")
                        say("You have done it, you have survived! Well done!")
                    }
                    else{
                        say("They pull a knife. You try to disarm them, but they are quicker, just a little bit.")
                        say("They strike you down. Not for our asset, but for your mechanical parts. Slowly the optical signal of your eyes cut out and fade to static.")
                        say("Thank you for playing, and better luck next time!")
                        buttons(
                                "Reboot"
                        )
                    }
                }

            }
        }

        state("/start/left") {
            activators{
                intent("left")
            }
            action{
                reactions.say("You went left")
            }
        }

        state("/start/right") {
            activators{
                intent("right")
            }
            action{
                reactions.say("You went right")
            }
        }

        state("bye") {
            activators {
                intent("Bye")
            }

            action {
                reactions.sayRandom(
                    "See you soon!",
                    "Bye-bye!"
                )
                //reactions.image("https://media.giphy.com/media/EE185t7OeMbTy/source.gif")
            }
        }

        state("smalltalk", noContext = true) {
            activators {
                anyIntent()
            }

            action {
                activator.caila?.topIntent?.answer?.let {
                    reactions.say(it)
                }
            }
        }

        fallback {
            reactions.sayRandom(
                "Speak up!"
            )
        }
    }


}

//RNG
//val wohin = (1..100).shuffled().first()
//val wohin2 = "/start/"
//val wohin3 = StringBuilder()
//wohin3.append(wohin2).append(wohin)
//val wohinfin = wohin3.toString()
//say(wohinfin)