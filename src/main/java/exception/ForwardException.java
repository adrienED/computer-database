package exception;

import org.springframework.web.bind.annotation.RequestMapping;

public class ForwardException extends Exception {

		private static final long serialVersionUID = -3636597197129815670L;

		public ForwardException() {
			super("Erreur ForwardException request");
		}

	}