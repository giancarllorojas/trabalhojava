package br.ufrj;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Obrigatorio {
	boolean value();
	
}
