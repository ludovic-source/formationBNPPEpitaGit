package com.example.SpringBatchTP1;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class ImportJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<BookDTO> bookItemReader() {
        return new FlatFileItemReaderBuilder<BookDTO>()
                .name("bookItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .delimiter(";")
                .names(new String[]{ "title", "author", "isbn", "publisher","year" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<BookDTO>() {{
                    setTargetType(BookDTO.class);
                }})
                .build();
    }

    @Bean
    public BookItemProcessor processor() {
        return new BookItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<BookDTO> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BookDTO>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO book (title, author, isbn, publisher, year)" + "" +
                        " VALUES (:title, :author, :isbn, :publisher, :year)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step importBookStep1(JdbcBatchItemWriter<BookDTO> writer) {
        return stepBuilderFactory.get("importBookStep1")
                .<BookDTO, BookDTO> chunk(10)
                .reader(bookItemReader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job importBookJob(JobCompletionNotificationListener listener, Step importBookStep1) {
        return jobBuilderFactory.get("importBookJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(importBookStep1)
                .end()
                .build();
    }



}
