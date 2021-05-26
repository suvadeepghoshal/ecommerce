package com.ecommerceDemo.core.services;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import org.osgi.service.component.annotations.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WorkflowProcess.class, immediate = true, property = { "process.label" + " = CustomWorkFlowStep" })
public class CustomWorkflowStep implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(CustomWorkflowStep.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
            throws WorkflowException {
        try {
            String name = metaDataMap.get("NAME", String.class);
            LOG.info("NAME: " + name); // We are getting the values that we are sending from the Custom Workflow Step
        } catch (Exception e) {
            LOG.error("Workflow Error: " + e.getMessage());
        }
    }

}
