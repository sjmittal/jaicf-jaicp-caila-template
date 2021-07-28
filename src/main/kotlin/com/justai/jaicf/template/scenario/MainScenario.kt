package com.justai.jaicf.template.scenario


import com.justai.jaicf.activator.rasa.rasa
import com.justai.jaicf.builder.Scenario

val mainScenario = Scenario {
    state("start") {
        activators {
            regex("/start")
            intent("greet")
        }
        action {
            val slots = activator.rasa?.slots
            reactions.run {
                sayRandom(slots?.values?.map { it.value }?.toList().orEmpty())
            }
        }
    }

    state("bye") {
        activators {
            intent("goodbye")
        }

        action {
            val slots = activator.rasa?.slots
            reactions.run {
                sayRandom(slots?.values?.map { it.value }?.toList().orEmpty())
            }
        }
    }

    state("smalltalk", noContext = true) {
        activators {
            anyIntent()
        }

        action {
            val slots = activator.rasa?.slots
            reactions.run {
                sayRandom(slots?.values?.map { it.value }?.toList().orEmpty())
            }
        }
    }

    fallback {
        reactions.sayRandom(
            "Sorry, I didn't get that...",
            "Sorry, could you repeat please?"
        )
    }
}