package org.osgiusers.germany.example.basar.web;

import org.osgi.framework.Bundle;
import org.osgi.service.packageadmin.PackageAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.osgi.extensions.annotation.ServiceReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

@Controller("refreshController")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RefreshController {

	private PackageAdmin packageAdmin;

	@ServiceReference
	public void setPackageAdmin(PackageAdmin packageAdmin) {
		this.packageAdmin = packageAdmin;
	}
	
	public String refreshAction()
	{
		Bundle bundle = packageAdmin.getBundle(RefreshController.class);
		packageAdmin.refreshPackages(new Bundle[]{bundle});
		return "OK";
	}
	
}
