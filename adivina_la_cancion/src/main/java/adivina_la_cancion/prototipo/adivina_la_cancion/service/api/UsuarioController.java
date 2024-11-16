package adivina_la_cancion.prototipo.adivina_la_cancion.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adivina_la_cancion.prototipo.adivina_la_cancion.domain.Usuario;
import adivina_la_cancion.prototipo.adivina_la_cancion.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) // Permitir solicitudes desde http://localhost:4200
public class UsuarioController {

    @Autowired
    protected UsuarioRepository ur;

    @PostMapping("/{usuarioNombre}")
    @Transactional
    public ResponseEntity<Usuario> crearUsuario(@PathVariable String usuarioNombre) {
        Usuario usuario = new Usuario(usuarioNombre);
        System.out.println("Usuario creado");
        return ResponseEntity.ok(ur.save(usuario));
    }
}
