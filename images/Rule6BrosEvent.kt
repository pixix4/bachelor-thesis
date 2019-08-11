fun Context.setupRule6() {

    match<BpmnTask, Event> { bpmn, bros ->
        matchStrings(bpmn.element.name, bros.element.desc)
    }
    match<BpmnTask, ReturnEvent> { bpmn, bros ->
        matchStrings(bpmn.element.name, bros.element.desc)
    }

    verifyBros<Event>("Rule 6 - BrosEvent") { bros ->
        for (element in bros.matchingElements) {
            val match = element.element as? BpmnElement ?: continue
            return@verifyBros Result.match("...", bpmn = element)
        }
        Result.error("...")
    }

    verifyBros<ReturnEvent>("Rule 6 - BrosReturnEvent") { bros ->
        for (element in bros.matchingElements) {
            val match = element.element as? BpmnElement ?: continue
            return@verifyBros Result.match("...", bpmn = element)
        }
        Result.error("...")
    }
}