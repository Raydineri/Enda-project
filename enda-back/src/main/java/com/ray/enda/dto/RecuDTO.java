package com.ray.enda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class RecuDTO {
    @NotBlank
    private String numeroRecudebut;

    @NotBlank
    private String numeroRecufin;

    @NotNull
    private MultipartFile upload;

    @NotBlank
    private String apGsm;

    // Getters and Setters

    public @NotBlank String getNumeroRecudebut() {
        return numeroRecudebut;
    }

    public void setNumeroRecudebut(@NotBlank String numeroRecudebut) {
        this.numeroRecudebut = numeroRecudebut;
    }

    public @NotBlank String getNumeroRecufin() {
        return numeroRecufin;
    }

    public void setNumeroRecufin(@NotBlank String numeroRecufin) {
        this.numeroRecufin = numeroRecufin;
    }

    public @NotNull MultipartFile getUpload() {
        return upload;
    }

    public void setUpload(@NotNull MultipartFile upload) {
        this.upload = upload;
    }

    public @NotBlank String getApGsm() {
        return apGsm;
    }

    public void setApGsm(@NotBlank String apGsm) {
        this.apGsm = apGsm;
    }
}
