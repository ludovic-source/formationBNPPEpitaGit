package com.example.SpringBatchTest1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.util.function.Function;

@Configuration
@EnableBatchProcessing
public class FusionJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job fusionFichiersJob(JobCompletionNotificationListener listener, Step fusionFichiersStep1) {
        return jobBuilderFactory.get("fusionFichiersJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(fusionFichiersStep1)
                .end()
                .build();
    }

    @Bean
    public Step fusionFichiersStep1(FlatFileItemWriter<MessageOutputDTO> writer) {
        return stepBuilderFactory.get("fusionFichiersStep1")
                .<MessageInput1DTO, MessageOutputDTO> chunk(10)
                .reader(message1ItemReader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<MessageInput1DTO> message1ItemReader() {
        return new FlatFileItemReaderBuilder<MessageInput1DTO>()
                .name("message1ItemReader")
                .resource(new ClassPathResource("fichierInput1.csv"))
                .delimited()
                .delimiter(";")
                .names(new String[]{ "messageInput1", "marqueVoitureInput1" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MessageInput1DTO>() {{
                    setTargetType(MessageInput1DTO.class);
                }})
                .build();
    }

    @Bean
    public FusionItemProcessor processor() {
        return new FusionItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<MessageOutputDTO> writer() {
        return new FlatFileItemWriterBuilder<MessageOutputDTO>()
                .name("itemWriter")
                .resource(new FileSystemResource("FichierOutput.csv"))
                //.append(true)
                .lineAggregator(new DelimitedLineAggregator<MessageOutputDTO>() {
                    {
                        setDelimiter(",");
                        setFieldExtractor(new BeanWrapperFieldExtractor<MessageOutputDTO>() {
                            {
                                setNames(new String[] { "messageInput1", "messageInput2", "marqueVoitureInput1", "typeInput2" });
                            }
                        });
                    }})
                .build();
    }

}
