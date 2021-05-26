package com.ecommerceDemo.core.servlets;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/executeworkflow"}) // The URL from which we trigger the servlet
public class ExecuteWorkflow extends SlingSafeMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ExecuteWorkflow.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
            throws ServletException, IOException {
        String status = "Workflow Executing";
        final ResourceResolver resourceResolver = req.getResourceResolver();
        String payload = req.getRequestParameter("page").getString(); // in the link we are writing /bin/executeworkflow?page=
        try {
            if (StringUtils.isNotBlank(payload)) { //   !payload.equals("")
                WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
                WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/ecommerceworkflow"); // we need to pass the path of the page where we are doing the workflow
                WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);
                status = workflowSession.startWorkflow(workflowModel, workflowData).getState();
            }

        } catch (Exception e) {
            LOG.info("\n ERROR IN WORKFLOW {} ", e.getMessage());
        }
        resp.setContentType("application/json"); // if you hit the model "/var/workflow/models/ecommerceworkflow" -> get the JSON O/P
        resp.getWriter().write(status);
    }

    

}
