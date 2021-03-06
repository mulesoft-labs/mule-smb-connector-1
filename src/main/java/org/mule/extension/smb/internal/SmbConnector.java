package org.mule.extension.smb.internal;

import static org.mule.runtime.extension.api.annotation.param.display.Placement.ADVANCED_TAB;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.mule.extension.file.common.api.FileConnectorConfig;
import org.mule.extension.file.common.api.exceptions.FileError;
import org.mule.extension.smb.internal.source.SmbDirectoryListener;
import org.mule.runtime.core.api.connector.ConnectionManager;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.Sources;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

/**
 * This is the main class of an extension, is the entry point from which
 * configurations, connection providers, operations and sources are going to be
 * declared.
 */
@Extension(name = "SMB")
@Operations({ SmbOperations.class })
@ConnectionProviders({ SmbConnectionProvider.class })
@ErrorTypes(FileError.class)
@Sources(SmbDirectoryListener.class)
@Xml(prefix = "smb")
public class SmbConnector extends FileConnectorConfig {

	@Inject
	private ConnectionManager connectionManager;

	 /**
	   * Wait time between size checks to determine if a file is ready to be read. This allows a file write to complete before
	   * processing. If no value is provided, the check will not be performed. When enabled, Mule performs two size checks waiting the
	   * specified time between calls. If both checks return the same value, the file is ready to be read.This attribute works in
	   * tandem with {@link #timeBetweenSizeCheckUnit}.
	   */
	  @Parameter
	  @Placement(tab = ADVANCED_TAB)
	  @Summary("Wait time between size checks to determine if a file is ready to be read.")
	  @Optional
	  private Long timeBetweenSizeCheck;

	  /**
	   * A {@link TimeUnit} which qualifies the {@link #timeBetweenSizeCheck} attribute.
	   * <p>
	   * Defaults to {@code MILLISECONDS}
	   */
	  @Parameter
	  @Placement(tab = ADVANCED_TAB)
	  @Optional(defaultValue = "MILLISECONDS")
	  @Summary("Time unit to be used in the wait time between size checks")
	  private TimeUnit timeBetweenSizeCheckUnit;
	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}
}
