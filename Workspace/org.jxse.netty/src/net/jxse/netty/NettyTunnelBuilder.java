package net.jxse.netty;

import java.util.ArrayList;
import java.util.Collection;

import net.jxta.impl.endpoint.netty.NettyTransport;
import net.jxta.impl.endpoint.netty.http.NettyHttpTunnelTransport;
import net.jxta.impl.modulemanager.AbstractJxtaModuleBuilder;
import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.module.IJxtaModuleBuilder;
import net.jxta.module.IJxtaModuleDescriptor;
import net.jxta.module.IModuleDescriptor;
import net.jxta.peergroup.core.Module;
import net.jxta.peergroup.core.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;

public class NettyTunnelBuilder extends AbstractJxtaModuleBuilder<Module> implements IJxtaModuleBuilder<Module> {

	public NettyTunnelBuilder() {
		super.addDescriptor( new NettyHttpDescriptor());
		super.addDescriptor( new NettyTcpDescriptor());
	}

	
	@Override
	public Module onBuildModule(IModuleDescriptor descriptor){
		if( !super.canBuild(descriptor))
			return null;
		if( descriptor instanceof NettyHttpDescriptor )
			return new NettyHttpTunnelTransport();
		if( descriptor instanceof NettyTcpDescriptor )
			return new NettyTransport();
		return null;
	}

	public IJxtaModuleDescriptor[] getDescriptors(ModuleSpecID specID) {
		Collection<IJxtaModuleDescriptor> results = new ArrayList<IJxtaModuleDescriptor>();
		for( IModuleDescriptor descriptor: super.getSupportedDescriptors() ){
			if( !( descriptor instanceof IJxtaModuleDescriptor ))
				continue;
			IJxtaModuleDescriptor jdesc = (IJxtaModuleDescriptor) descriptor;
			if( specID.equals( jdesc.getModuleSpecID() ))
				results.add( jdesc );
		}
		return results.toArray( new IJxtaModuleDescriptor[ results.size()]);
	}

	public IJxtaModuleDescriptor getDescriptor(ModuleImplAdvertisement implAdv) {
		for( IModuleDescriptor descriptor: super.getSupportedDescriptors() ){
			if( !( descriptor instanceof IJxtaModuleDescriptor ))
				continue;
			IJxtaModuleDescriptor jdesc = (IJxtaModuleDescriptor) descriptor;
			if( implAdv.equals( jdesc.getModuleImplAdvertisement() ))
				return jdesc;
		}
		return null;
	}

	@Override
	public Class<? extends Module> getRepresentedClass(
			IModuleDescriptor descriptor) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create the HTTP descriptor
	 * @author Kees
	 *
	 */
	private static class NettyHttpDescriptor extends AbstractJxtaModuleDescriptor{

		private static final String S_HTTP_NETTY_IDENTIFIER = "net.jxta.impl.endpoint.netty.http.NettyHttpTunnelTransport";
		private static final String S_HTTP_NETTY_DESCRIPTION = " HTTP tunneling message transport";
		private static final String S_HTTP_NETTY_MODULE_SPEC_ID = "urn:jxta:uuid-E549DB3BCBCF4789A392B6100B78CC55F127AD1AADF0443ABF6FBDFD7909876906";
		private static final String S_HTTP_NETTY_VERSION ="0.9.2"; 

		NettyHttpDescriptor() {
			super();
		}

		@Override
		public void prepare() {
			super.setIdentifier(S_HTTP_NETTY_IDENTIFIER);
			super.setRefClass( S_HTTP_NETTY_IDENTIFIER );
			super.setDescription( S_HTTP_NETTY_DESCRIPTION );
			super.setVersion( S_HTTP_NETTY_VERSION );
			super.setSpecID( S_HTTP_NETTY_MODULE_SPEC_ID );
		}
		
		@Override
		public boolean onInitialised(){ 
			return true;
		}
	}

	/**
	 * Create the TCP descriptor
	 * @author Kees
	 *
	 */
	private static class NettyTcpDescriptor extends AbstractJxtaModuleDescriptor{

		private static final String S_HTTP_NETTY_IDENTIFIER = "net.jxta.impl.endpoint.netty.NettyTransport";
		private static final String S_HTTP_NETTY_DESCRIPTION = " Reference Implementation of the TCP Message Transport";
		private static final String S_HTTP_NETTY_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000090106";
		private static final String S_HTTP_NETTY_VERSION ="9.6.0"; 

		NettyTcpDescriptor() {
			super();
		}

		@Override
		public void prepare() {
			super.setIdentifier(S_HTTP_NETTY_IDENTIFIER);
			super.setRefClass( S_HTTP_NETTY_IDENTIFIER );
			super.setDescription( S_HTTP_NETTY_DESCRIPTION );
			super.setVersion( S_HTTP_NETTY_VERSION );
			super.setSpecID( S_HTTP_NETTY_MODULE_SPEC_ID );
		}
		
		@Override
		public boolean onInitialised()  {
			return true;
		}
	}
}