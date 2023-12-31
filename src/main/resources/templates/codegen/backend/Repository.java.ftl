package templates.codegen.backend;

import com.example.template.modules.${moduleLowerCase}.model.${moduleUpperCase}${nameUpperCase};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author created by ${author} on ${date}
 */
@Repository
public interface ${moduleUpperCase}${nameUpperCase}Repository extends JpaRepository<${moduleUpperCase}${nameUpperCase}, String> {

}