package com.informanaging.project.demo.repository;

import com.informanaging.project.demo.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class BlockRepositoryTest {

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void crud() {
        Block block = new Block();
        block.setName("martin");
        block.setBlockReason("not friendly");
        block.setBlockStartDate(LocalDate.now());
        block.setBlockEndDate(LocalDate.now());

        blockRepository.save(block);

        blockRepository.findAll().forEach(System.out::println);
//        List<Block> blocks = blockRepository.findAll();
//
//        assertThat(blocks.size()).isEqualTo(3);
//        assertThat(blocks.get(0).getName()).isEqualTo("dennis");
//        assertThat(blocks.get(1).getName()).isEqualTo("sophia");
//        assertThat(blocks.get(2).getName()).isEqualTo("martin");
    }
}