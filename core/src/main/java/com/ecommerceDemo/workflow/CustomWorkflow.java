package com.ecommerceDemo.workflow;

import javax.jcr.Session;
import javax.jcr.Node;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import org.osgi.service.component.annotations.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1) Implement the Interface WorkflowProcess 2) We want to make the class a
 * component so that it is recognised by AEM 3) We want to use this component as
 * a service later on so adding service for the WorkflowProcess interface 4)
 * Since this is going to be a custom workflow process a custom label is
 * mandatory For which we are using the process.label On following the above 4
 * steps the custom workflow should be generated
 */

@Component(service = WorkflowProcess.class, immediate = true, property = {
        "process.label" + "= Ecommerce Custom Workflow Process" })
public class CustomWorkflow implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(CustomWorkflow.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
            throws WorkflowException {
        try {
            // We will create a work flow data fom the work flow items
            WorkflowData workflowData = workItem.getWorkflowData();
            // check if the payload we are getting from the workflowdata is same as JCR_PATH
            // or not
            if (workflowData.getPayload().equals("JCR_PATH")) {
                // create a session
                Session session = workflowSession.adaptTo(Session.class); // we are trying to get the workflow session
                // get the payload from the workflow data and then convert it into the path:
                // /jcr:content
                String path = workflowData.getPayload().toString() + "/jcr:content"; // this payload will be the path of
                                                                                     // the page
                // we will then create a node from the path
                Node node = (Node) session.getItem(path);
                // then we will process the argument that we enter in the workflow process
                // we will get every value from the dialog using the meta data map

                // Here we are getting the dialog properties

                String[] processArgs = metaDataMap.get("PROCESS_ARGS", "string").toString().split(",");
                MetaDataMap wfd = workItem.getWorkflow().getWorkflowData().getMetaDataMap();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
