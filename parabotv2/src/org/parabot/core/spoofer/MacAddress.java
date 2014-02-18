package org.parabot.core.spoofer;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.Enumeration;

public class MacAddress {

	public static byte[] mac = new byte[] { 11, 11, 11, 11, 11, 11 };

	private static byte[] realMac;

	private static MacAddress cached;

	static {
        try {
			mac = getRealHardwareAddress();
		} catch (Exception ignored) {
		}
	}

	public byte[] getHardwareAddress() {
		return mac;
	}

	public static byte[] getRealHardwareAddress() throws SocketException {
		if (realMac != null)
			return realMac;
		Enumeration<java.net.NetworkInterface> nis = java.net.NetworkInterface
				.getNetworkInterfaces();
		while (nis.hasMoreElements()) {
			try {
				byte[] b = nis.nextElement().getHardwareAddress();
				if (b.length == 0)
					continue;
				return realMac = b;
			} catch (Exception e) {
			}
		}
		return mac;
	}

	public static MacAddress getByInetAddress(InetAddress addr) {
		if (cached == null)
			cached = new MacAddress();
		return cached;
	}
}