package org.training.controller.command.orderCommand;

import org.training.constant.jsp.RequestAttributes;
import org.training.controller.command.Command;
import org.training.controller.util.OtherUtil;
import org.training.util.logUtil.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for confirming current order
 */
public class ConfirmOrderCommand implements Command {

    /**
     *
     * @param request request
     * @param response response
     * @return way to home page depends on user role
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(RequestAttributes.CURRENT_ORDER);
        logger.info(LogMessageBuilder.INSTANCE.confirmOrderInfo((String) request.getAttribute(RequestAttributes.USER_NAME)));
        return OtherUtil.getUserIndexPage(request);
    }
}
