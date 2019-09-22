fun Context.setupRule6() {
    match<BpmnGateway, BrosEvent> { bpmn, bros ->
        bpmn.relations<BpmnSequenceFlow>().any { flow ->
            flow.relation.name.isNotBlank() &&
                    matchStrings(
                        flow.relation.name, 
                        bros.element.desc
                    )
        }
    }
    match<BpmnGateway, BrosReturnEvent> { bpmn, bros ->
        bpmn.relations<BpmnSequenceFlow>().any { flow ->
            flow.relation.name.isNotBlank() &&
                    matchStrings(
                        flow.relation.name, 
                        bros.element.desc
                    )
        }
    }

    match<BpmnTask, BrosEvent> { bpmn, bros ->
        matchStrings(bpmn.element.name, bros.element.desc)
    }
    match<BpmnTask, BrosReturnEvent> { bpmn, bros ->
        matchStrings(bpmn.element.name, bros.element.desc)
    }

    verifyBros<BrosEvent> { bros ->
        for (bpmn in bros.matchingElements) {
            if (bpmn.checkType<BpmnElement>()) {
                return@verifyBros Result.match("...", bpmn = bpmn)
            }
        }
        Result.error(".")
    }
    verifyBros<BrosReturnEvent> { bros ->
        for (bpmn in bros.matchingElements) {
            if (bpmn.checkType<BpmnElement>()) {
                return@verifyBros Result.match("...", bpmn = bpmn)
            }
        }
        Result.error("...")
    }
}