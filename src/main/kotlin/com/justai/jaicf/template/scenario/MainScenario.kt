package com.justai.jaicf.template.scenario


import com.justai.jaicf.activator.rasa.rasa
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.channels.TelephonyEvents
import com.justai.jaicf.helpers.logging.logger

val mainScenario = Scenario {
    state("start") {
        activators {
            regex("/start")
            intent("greet")
        }
        action {
            reactions.run {
                sayRandom(
                    "Hello! Reverie Bot welcomes you. ask me for capital of any indian state and I should be able to tell.",
                    "Hi there! Welcome to Reverie Bot service. I can help you in finding out the capital of any indian state."
                )
            }
        }
    }

    state("answer") {
        activators {
            intent("capital_query")
        }

        action {
            // Fills slots in intent
            val slots = activator.rasa?.slots.orEmpty()
            var nameOfState: String? = ""
            // Recognizes entities in query
            if (!slots?.keys?.isEmpty() || slots.containsKey("region")) {
                val region = slots.get("region")
                logger.debug("Region: ${region}")
                nameOfState = region?.value;

                //Andhra Pradesh
                if (nameOfState?.contains("Andhra", true) == true) {
                    reactions.say("The administrative capital of $nameOfState is Visakhapatnam and legislative capital is Amravati")
                }
                //Arunachal Pradesh
                else if (nameOfState?.contains("Arunachal", true) == true) {
                    reactions.say("The capital of $nameOfState is Itanagar")
                }
                //Assam
                else if (nameOfState.equals("Assam", true)) {
                    reactions.say("The capital of $nameOfState is Dispur and guwahati is it's largest city")
                }
                //Bihar
                else if (nameOfState.equals("Bihar", true)) {
                    reactions.say("The capital of $nameOfState is patna")
                }
                //Chhattisgarh
                else if (nameOfState.equals("Chhattisgarh", true)) {
                    reactions.say("The capital of $nameOfState is raipur")
                }
                //Goa
                else if (nameOfState.equals("goa", true)) {
                    reactions.say("The capital of $nameOfState is panaji")
                }
                //Haryana
                else if (nameOfState.equals("haryana", true)) {
                    reactions.say("The capital of $nameOfState is Chandigarh")
                }
                //Himachal Pradesh
                else if (nameOfState?.contains("Himachal", true) == true) {
                    reactions.say("The capital of $nameOfState is Shimla")
                }
                //Jharkhand
                else if (nameOfState.equals("Jharkhand", true)) {
                    reactions.say("The capital of $nameOfState is ranchi and jamshedpur is the largest city")
                }
                //Karnataka
                else if (nameOfState.equals("karnataka", true)) {
                    reactions.say("The capital of $nameOfState is bengaluru, which is also known as bangalore")
                }
                //Kerala
                else if (nameOfState.equals("kerala", true)) {
                    reactions.say("The capital of $nameOfState is Thiruvananthapuram")
                }
                //Madhya Pradesh
                else if (nameOfState?.equals("Madhya", true) == true) {
                    reactions.say("The capital of $nameOfState is bhopal")
                }
                //Maharashtra
                else if (nameOfState.equals("Maharashtra", true)) {
                    reactions.say("Mumbai is the summer capital of $nameOfState and nagpur is it's winter capital")
                }
                //Manipur
                else if (nameOfState.equals("Manipur", true)) {
                    reactions.say("The capital of $nameOfState is imphal.")
                }
                //Meghalaya
                else if (nameOfState.equals("Meghalaya", true)) {
                    reactions.say("The capital of $nameOfState is shillong.")
                }
                //Mizoram
                else if (nameOfState.equals("Mizoram", true)) {
                    reactions.say("The capital of $nameOfState is Aizawl.")
                }
                //Nagaland
                else if (nameOfState.equals("Nagaland", true)) {
                    reactions.say("Kohima is the capital of $nameOfState.")
                }
                //Odisha
                else if (nameOfState.equals("odisha", true)) {
                    reactions.say("Temple city bhubaneshwar  is the capital of $nameOfState.")
                } else if (nameOfState.equals("orissa", true)) {
                    reactions.say("Temple city bhubaneshwar  is the capital of $nameOfState.")
                }
                //Punjab
                else if (nameOfState.equals("Punjab", true)) {
                    reactions.say("Chandigarh, the city built by Le Corbusier, is the capital of $nameOfState.")
                }
                //Rajasthan
                else if (nameOfState.equals("Rajasthan", true)) {
                    reactions.say("Pink city Jaipur is the capital of $nameOfState.")
                }
                //Sikkim
                else if (nameOfState.equals("Sikkim", true)) {
                    reactions.say("$nameOfState is one of the most beautiful state, whose capital is Gangtok")
                }
                //Tamil Nadu
                else if (nameOfState?.contains("Tamil", true) == true) {
                    reactions.say("the erstwhile madras, which got renamed as chennai, is the capital of $nameOfState.")
                }
                //Telangana
                else if (nameOfState.equals("telangana", true)) {
                    reactions.say("Hyderabad is the capital of this newly formed state $nameOfState.")
                }
                //Tripura
                else if (nameOfState.equals("tripura", true)) {
                    reactions.say("the capital of $nameOfState is agartala.")
                }
                //Uttar Pradesh
                else if (nameOfState?.equals("uttar", true) == true) {
                    reactions.say("the capital of $nameOfState is lucknow.")
                }
                //Uttarakhand
                else if (nameOfState.equals("uttarakhand", true)) {
                    reactions.say("the capital of $nameOfState is the hill town of dehradun.")
                }
                //West Bengal
                else if (nameOfState?.contains("bengal", true) == true) {
                    reactions.say("the cultural capital of our country, Kolkata is the capital of $nameOfState.")
                }
                //gujarat
                else if (nameOfState.equals("gujarat", true)) {
                    reactions.say("Named after our father of nation, The capital of $nameOfState is the Gandhinagar")
                } else {
                    reactions.say("Sorry, $nameOfState is not in my database")
                }
            } else {
                reactions.say("Sorry, could not fetch the requested state.")
            }
        }
    }

    state("bye") {
        activators {
            intent("goodbye")
        }

        action {
            reactions.sayRandom(
                "Hope you enjoyed interacting with me. See you soon!",
                "Bye-bye!"
            )
        }
    }

    state("thanks") {
        activators {
            intent("thanks")
        }

        action {
            reactions.sayRandom(
                "you are most welcome",
                "My Pleasure",
                "Glad to help"
            )
        }
    }

    state("speechNotRecognized") {
        activators {
            event(TelephonyEvents.SPEECH_NOT_RECOGNISED)
        }
        action {
            reactions.say("Sorry, I can't hear you! Could you repeat please?")
        }
    }

    state("smalltalk", noContext = true) {
        activators {
            intent("bot_challenge")
        }

        action {
            reactions.sayRandom("Yep! I'm a bot.", "Yes, I am.")
        }
    }

    fallback {
        reactions.sayRandom(
            "Sorry, I didn't get that...",
            "Sorry, could you repeat please?"
        )
    }
}