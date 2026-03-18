package com.yash.bulk_processing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.bulk_processing.entities.FileJob;

public interface FileJobRepository extends JpaRepository<FileJob, String> {

}
