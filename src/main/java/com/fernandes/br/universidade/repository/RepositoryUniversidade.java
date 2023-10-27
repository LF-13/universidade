package com.fernandes.br.universidade.repository;

import com.fernandes.br.universidade.model.ModelAluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryUniversidade extends JpaRepository<ModelAluno, UUID> {


}
