package com.ps.emakers.API_PS.service;

import com.ps.emakers.API_PS.data.dto.request.LivroRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.LivroResponseDTO;
import com.ps.emakers.API_PS.data.entity.Livro;
import com.ps.emakers.API_PS.exceptions.general.EntityNotFoundException;
import com.ps.emakers.API_PS.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroResponseDTO> getAllLivro(){
        List<Livro> Livros = livroRepository.findAll();

        return Livros.stream().map(LivroResponseDTO::new).collect(Collectors.toList());
    }

    public LivroResponseDTO getLivroById(Long idLivro){
        Livro livro = getLivroEntityById(idLivro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO createLivro(LivroRequestDTO livroRequestDTO){
        Livro livro = new Livro(livroRequestDTO);
        livro.setName(livroRequestDTO.name());
        livro.setAutor(livroRequestDTO.autor());
        livro.setDataLancamento(livroRequestDTO.dataLancamento());

        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO updateLivro(Long idLivro, LivroRequestDTO livroRequestDTO){
        Livro livro = getLivroEntityById(idLivro);

        livro.setName(livroRequestDTO.name());
        livro.setAutor(livroRequestDTO.autor());
        livro.setDataLancamento(livroRequestDTO.dataLancamento());

        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    public String deleteLivro(Long idLivro){
        Livro livro = getLivroEntityById(idLivro);
        livroRepository.delete(livro);

        return "Livro id:" + idLivro + " deletado com sucesso!";
    }

    private Livro getLivroEntityById(Long idLivro){
        return livroRepository.findById(idLivro).orElseThrow(()-> new EntityNotFoundException(idLivro));
    }
}
