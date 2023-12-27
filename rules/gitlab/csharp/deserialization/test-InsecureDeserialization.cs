// License: LGPL-3.0 License (c) security-code-scan

using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using RequestDataDemo.Models;
using System.Collections.Generic;
using System.Collections;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization.Formatters.Soap;
using System.Runtime.Serialization;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Web.UI;
using System.Web;
using System.Xml;
using System;


class InsecureDeserialization {
    private void ConvertData(string json)
    {
        // ok: csharp_deserialization_rule-InsecureDeserialization
        JsonSerializer.Deserialize<string>(json);
    }
}

namespace RequestDataDemo.Controllers
{
    [Serializable]
    public class ExampleData
    {
        public string Username { get; set; }
        public int Age { get; set; }
    }

    [ApiController]
    [Route("api/[controller]")]
    public class RequestDataController : ControllerBase
    {
        private readonly ILogger<RequestDataController> _logger;

        public RequestDataController(ILogger<RequestDataController> logger)
        {
            _logger = logger;
        }

        [HttpGet("cookie")]
        public ActionResult<string> GetFromCookie([FromQuery] string key)
        {
            var cookie = Request.Cookies[key];

            byte[] data = Convert.FromBase64String(cookie.Value);

            MemoryStream memoryStream = new MemoryStream(data);
            BinaryFormatter formatter = new BinaryFormatter();
            // ruleid: csharp_deserialization_rule-InsecureDeserialization
            ExampleData deserializedData = (ExampleData)formatter.Deserialize(memoryStream);

            return Ok(deserializedData.Username);
        }

        [HttpGet("header")]
        public ActionResult<string> GetFromHeader([FromHeader] string headerName)
        {
            var headerVal = Request.Headers[headerName];

            byte[] data = Convert.FromBase64String(headerVal);

            MemoryStream memoryStream = new MemoryStream(data);
            SoapFormatter formatter = new SoapFormatter();
            // ruleid: csharp_deserialization_rule-InsecureDeserialization
            ExampleData deserializedData = (ExampleData)formatter.Deserialize(memoryStream);

            return Ok(deserializedData.Username);
        }

        [HttpPost("form")]
        public ActionResult<string> GetFromForm([FromForm] string key)
        {
            var formVal = Request.Form[key];

            byte[] data = Convert.FromBase64String(formVal);

            MemoryStream memoryStream = new MemoryStream(data);
            NetDataContractSerializer formatter = new NetDataContractSerializer();
            // ruleid: csharp_deserialization_rule-InsecureDeserialization
            ExampleData deserializedData = (ExampleData)formatter.Deserialize(memoryStream);

            return Ok(deserializedData.Username);
        }

        [HttpGet("query")]
        public ActionResult<string> GetFromQuery([FromQuery] string param)
        {
            var queryVal = Request.QueryString[param];

            byte[] data = Convert.FromBase64String(queryVal);

            MemoryStream memoryStream = new MemoryStream(data);
            LosFormatter formatter = new LosFormatter();
            // ruleid: csharp_deserialization_rule-InsecureDeserialization
            ExampleData deserializedData = (ExampleData)formatter.Deserialize(memoryStream);

            return Ok(deserializedData.Username);
        }
    }

    public static string FromHttpContext(string key)
    {
        HttpContext context = new DefaultHttpContext();
        string cookieValue = context.Request.Cookies[key];
        byte[] data = Convert.FromBase64String(cookieValue);

        MemoryStream memoryStream = new MemoryStream(data);
        BinaryFormatter formatter = new BinaryFormatter();
        // ruleid: csharp_deserialization_rule-InsecureDeserialization
        ExampleData deserializedData = (ExampleData)formatter.Deserialize(memoryStream);

        return Ok(deserializedData.Username);
    }

    public static string FromEnvVar(string name)
    {
        string val = Environment.GetEnvironmentVariable(name);

        byte[] data = Convert.FromBase64String(val);
        MemoryStream memoryStream = new MemoryStream(data);
        BinaryFormatter formatter = new BinaryFormatter();

        // ruleid: csharp_deserialization_rule-InsecureDeserialization
        ExampleData deserializedData = (ExampleData)formatter.Deserialize(memoryStream);

        return Ok(deserializedData.Username);
    }

    public static ExampleData ReadAndDeserializeFile(string filePath)
    {
        // Check if the file exists
        if (File.Exists(filePath))
        {
            string content = File.ReadAllText(filePath);
            byte[] data = Encoding.UTF8.GetBytes(content);

            using (Stream stream = new MemoryStream(data))
            {
                BinaryFormatter formatter = new BinaryFormatter();
                // ruleid: csharp_deserialization_rule-InsecureDeserialization
                return (ExampleData)formatter.Deserialize(data).Username;
            }
        }

        return null;
    }

    public static string SafeExample(string fileName)
    {
        FileStream fs = new FileStream(fileName, FileMode.Open);
        XmlDictionaryReader reader = XmlDictionaryReader.CreateTextReader(fs, new XmlDictionaryReaderQuotas());
        DataContractSerializer serializer = new DataContractSerializer(typeof(ExampleData));

        // ok: csharp_deserialization_rule-InsecureDeserialization
        ExampleData deserializedData = (ExampleData)serializer.ReadObject(reader, true);

        reader.Close();
        fs.Close();

        return deserializedData.Username;
    }
}
