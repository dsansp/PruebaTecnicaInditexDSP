/**
 * The GNU GENERAL PUBLIC LICENSE (GPLv3)
 *  
 * Copyright (C) 2018  Francisco José Fernández González, Estefanía Fernández Muñoz
 *  
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package exception;

/**
 * <p>
 * This class is to configure all the Test Configuration.
 * </p>
 *
 *
 */
public class CustomAssertHandler{

	/**
	 * Protect constructor since it is a static only class
	 */
	protected CustomAssertHandler() {
		// Empty constructor
	}

	/**
	 * Fails a test with the given message and wrapping the original exception.
	 *
	 * @param message
	 *            the assertion error message
	 * @param realCause
	 *            the original exception
	 */
	public static void handlerError(String message, Throwable realCause) {
		AssertionError ae = new AssertionError(message);
		ae.initCause(realCause);
		throw ae;
	}
	

	/**
	 * Fails a test with the given message.
	 * 
	 * @param message
	 *            the assertion error message
	 */
	public static void handlerError(String message) {
		throw new AssertionError(message);
	}

	/**
	 * Fails a test with no message.
	 */
	public static void handlerError() {
		handlerError(null);
	}

}
