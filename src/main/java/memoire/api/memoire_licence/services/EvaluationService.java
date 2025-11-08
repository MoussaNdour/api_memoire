package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.response.EvaluationDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationService {
    public List<EvaluationDTO> findAll() {
        return new ArrayList<>();
    }

    public EvaluationDTO findById(int idevaluation) {
        return new EvaluationDTO();
    }

    public boolean create(EvaluationDTO evaluationDTO) {
        return false;
    }

    public void save(int idevaluation, EvaluationDTO evaluationDTO) {
    }

    public boolean update(EvaluationDTO evaluationDTO) {
        return false;
    }

    public boolean partialUpdate(int idevaluation, EvaluationDTO evaluationDTO) {
        return false;
    }

    public boolean deleteById(int idevaluation) {
        return false;
    }
}
